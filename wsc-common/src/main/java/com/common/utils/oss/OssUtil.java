package com.common.utils.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.common.config.OSSConfig;
import com.common.enumes.oss.OssBucketNameEnum;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * aliyun oss util
 *
 */
@Component
public class OssUtil {

	private static Logger logger = LoggerFactory.getLogger(OssUtil.class);

	@Autowired
	private OSSConfig ossConfig;
	private static final String RefreshObjectCaches = "RefreshObjectCaches";
	private static OSSClient client = null;

	@PostConstruct
	private void init(){
		// 初始化OSSClient
		if(client == null){
			logger.debug("oss service init ......");
			client = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param bucketName
	 *            bucket name
	 * @param key
	 *            object name
	 * @param filename
	 *            文件存放路径
	 */
	public static void downloadFile(String bucketName, String key,
			String filename) {
		OssUtil.client.getObject(new GetObjectRequest(bucketName, key), new File(filename));
	}

	/**
	 * 上传文件
	 * 
	 * @param bucketName
	 *            bucket name
	 * @param key
	 *            object name
	 * @param filePath
	 *            文件地址
	 * @throws FileNotFoundException
	 */
	public static void putObject(String bucketName, String key, String filePath)
			throws FileNotFoundException {
		// 获取指定文件的输入流
		File file = new File(filePath);
		InputStream content = new FileInputStream(file);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(file.length());
		// 上传Object.
		PutObjectResult result = client.putObject(bucketName, key, content, meta);
		if(logger.isDebugEnabled())
			logger.debug("put object result tag :{}",result.getETag());
	}
	
	/**
	 * 文件上传
	 * @author bowen	
	 * @date 2015年4月21日 下午8:38:13 
	 * @param bucketName
	 * @param key
	 * @param in
	 * @param length
	 * @throws FileNotFoundException
	 */
	public static void putObject(String bucketName,String key, InputStream in , long length) 
			throws FileNotFoundException{
		//创建bucket
//		createBucket(bucketName);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(length);
		if(key.contains(".html")){
			meta.setContentType("text/html");
		}else if(key.contains(".gif")){
			meta.setContentType("image/gif");
		}else{
			meta.setContentType("image/jpeg");
		}
		// 上传Object.
		PutObjectResult result = client.putObject(bucketName, key, in, meta);
		if(logger.isDebugEnabled())
			logger.debug("put object result tag :{}",result.getETag());
	}
	/**
	 * 删除指定bucket下面的key,key 可带文件夹名字
	 * @author banchun	
	 * @date 2015年6月19日 上午9:26:32 
	 * @param bucketName  阿里云 bucketName
	 * @param key  文件夹+文件名字
	 */
	public static void deleteObject(String bucketName,String key){
		if(logger.isInfoEnabled())
			logger.info("start delete object,bucketName:{},key:{}",bucketName,key);
		OssUtil.client.deleteObject(bucketName, key);
	}
	
	/**
	 * 删除一个Bucket和其中的Objects
	 * 
	 * @param bucketName
	 * @throws OSSException
	 * @throws ClientException
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static void deleteBucket(String bucketName) throws OSSException,
			ClientException {
		ObjectListing ObjectListing = client.listObjects(bucketName);
		List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
		for (int i = 0; i < listDeletes.size(); i++) {
			String objectName = listDeletes.get(i).getKey();
			// 如果不为空，先删除bucket下的文件
			client.deleteObject(bucketName, objectName);
		}
		client.deleteBucket(bucketName);
	}

	/**
	 * 新建bucket
	 * 
	 * @param bucketName
	 */
	public static void createBucket(String bucketName) {

		try {
			// 新建一个Bucket
			client.createBucket(bucketName);
			System.out.println("create bucket success");
			if (logger.isInfoEnabled())
				logger.info("create bucket success");
		} catch (Exception e) {
			logger.error("create bucket error,errorStack:{}",e);
		}
	}

	/**
	 * 删除掉Bucket
	 * 
	 * @param bucketName
	 *            bucket name
	 * @throws OSSException
	 * @throws ClientException
	 */
	@SuppressWarnings("unused")
	private static void deleteBucketForBlock(String bucketName)
			throws OSSException, ClientException {

		// 删除bucket之前必须保证bucket为空，所以先必须先删除object和multipart

		// 如果存在，查看bucket是否为空
		ObjectListing ObjectListing = client.listObjects(bucketName);
		List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
		for (int i = 0; i < listDeletes.size(); i++) {
			String objectName = listDeletes.get(i).getKey();
			// 如果不为空，先删除bucket下的文件
			client.deleteObject(bucketName, objectName);
		}

		// 删除所有未完成的multipart uploads.
		ListMultipartUploadsRequest listMultipartUploadsRequest = new ListMultipartUploadsRequest(
				bucketName);
		MultipartUploadListing uploadListing = client
				.listMultipartUploads(listMultipartUploadsRequest);

		for (MultipartUpload upload : uploadListing.getMultipartUploads()) {
			String key = upload.getKey();
			AbortMultipartUploadRequest abortMultipartUploadRequest = new AbortMultipartUploadRequest(
					bucketName, key, upload.getUploadId());

			client.abortMultipartUpload(abortMultipartUploadRequest);
		}

		// 删除bucket
		client.deleteBucket(bucketName);
	}

	/**
	 * 列出bucket里的object
	 * 
	 * @param bucketName
	 */
	public void listObjects(String bucketName) {

		// 获取指定bucket下的所有Object信息
		ObjectListing listing = client.listObjects(bucketName);
		// 遍历所有Object
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			System.out.println(objectSummary.getKey());
		}
	}

	/**
	 * 处理指定bucket里的指定object
	 * 
	 * @param bucketName
	 *            bucket
	 * @param key
	 *            objectName
	 * @throws IOException
	 */
	public void getObject(String bucketName, String key) throws IOException {

		// 获取Object，返回结果为OSSObject对象
		OSSObject object = client.getObject(bucketName, key);
		// 获取Object的输入流
		InputStream objectContent = object.getObjectContent();
		// 处理Object
		System.out.println("此处开始处理文件信息 ...");

		// 文件处理
		// 。。。

		// 关闭流
		objectContent.close();
	}

//	/**
//	 * 根据bucket和key生成签名的url
//	 *
//	 * @param bucket
//	 * @param key
//	 * @return
//	 */
//	public String getFileUrl(String bucket, String key) {
//		// http://cglin.oss-cn-hangzhou.aliyuncs.com/2.png?Expires=1427091425&OSSAccessKeyId=Il5AfBvWmZHM56Ne&Signature=ayu4JGwr3MVGpoEGgcPE9IiJ6Lc%3D
//		Date expires = new Date(new Date().getTime() + 1000 * seconds);
//		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
//				bucket, key);
//		generatePresignedUrlRequest.setExpiration(expires);
//		URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
//		return url.toString();
//	}
	
	private static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }
	/**
	 * 刷新obejct
	 * @author banchun	
	 * @date 2015年8月20日 上午10:49:25 
	 * @param objectPath 输入示例：abc.com/image/1.png
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@SuppressWarnings({ "resource" })
//	public static String refreshObject(String objectPath) throws InvalidKeyException, NoSuchAlgorithmException, ClientProtocolException, IOException{
//		String url = "http://cdn.aliyuncs.com/?&";
//		String objectType = "Directory";
//		String Format = "JSON";
//		String Version = "2014-11-11";
//		String AccessKeyId = accessKeyId;
//		String SignatureMethod = "HMAC-SHA1";
//		String Timestamp = formatIso8601Date(new Date());
//		String SignatureVersion = "1.0";
//		String SignatureNonce = UUID.randomUUID().toString();
//		AliyunSignature aliyunSign = new AliyunSignature();
////
//		SortedMap<String, String> map = new TreeMap<String, String>();
//		map.put("Format", Format);
//		map.put("Version", Version);
//		map.put("AccessKeyId", AccessKeyId);
//		map.put("SignatureMethod", SignatureMethod);
//		map.put("Timestamp", Timestamp);
//		map.put("SignatureVersion", SignatureVersion);
//		map.put("SignatureNonce", SignatureNonce);
//		map.put("Action", RefreshObjectCaches);
//		map.put("ObjectPath", objectPath);
//		map.put("ObjectType", objectType);
//		String sign = "";
//		try {
//			sign = aliyunSign.computeSignature(map, accessKeySecret);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("【"+objectPath+"】生成签名错误："+e.getMessage());
//			return "";
//		}
//		System.out.println(sign);
//		// 创建HttpClient实例
//        HttpClient httpclient = new DefaultHttpClient();
//        httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
//        // 创建Get方法实例
//        String getUrl = url + paramsToQueryString(map);
//        HttpGet httpgets = new HttpGet(getUrl+"&"+URLEncoder.encode("Signature", "UTF-8")+"="+URLEncoder.encode(sign, "UTF-8"));
//        HttpResponse response = httpclient.execute(httpgets);
//        HttpEntity entity = response.getEntity();
//        String result = "";
//        if (entity != null) {
//            InputStream instreams = entity.getContent();
//            result = convertStreamToString(instreams);
//            logger.error("刷新["+objectPath+"]结果："+result);
//            httpgets.abort();
//        }
//        return result;
//	}
	
	private static String paramsToQueryString(Map<String, String> params) throws UnsupportedEncodingException  {
        if (params == null || params.size() == 0){
            return null;
        }

        StringBuilder paramString = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> p : params.entrySet()){
            String key = p.getKey();
            String val = p.getValue();

            if (!first){
                paramString.append("&");
            }

            paramString.append(URLEncoder.encode(key, "UTF-8"));

            if (val != null){
                paramString.append("=").append(
                        URLEncoder.encode(val, "UTF-8"));
            }

            first = false;
        }
        return paramString.toString();
    }

	public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
	public static void main(String[] args) {
//		try {
//			refreshObject("http://image.mamahao.com/14302A0753");
//		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		OssUtil.downloadFile("apps-download", "ios-jspatch/V2_2_0.zip", "e://V2_2_01.zip");


	}
}
