package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;




@WebServlet("/upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("coming.......");         
        //�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ  
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");  
        //�ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼  
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");  
        File tmpFile = new File(tempPath);  
        if (!tmpFile.exists()) {  
            //������ʱĿ¼  
            tmpFile.mkdir();  
        }  
          
        //��Ϣ��ʾ  
        String message = "";  
        try{  
            //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺  
            //1������һ��DiskFileItemFactory����  
            DiskFileItemFactory factory = new DiskFileItemFactory();  
            //���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼���С�  
            factory.setSizeThreshold(1024*100);//���û������Ĵ�СΪ100KB�������ָ������ô�������Ĵ�СĬ����10KB  
            //�����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼  
            factory.setRepository(tmpFile);  
            //2������һ���ļ��ϴ�������  
            ServletFileUpload upload = new ServletFileUpload(factory);  
            //�����ļ��ϴ�����  
            /*upload.setProgressListener(new ProgressListener(){ 
                public void update(long pBytesRead, long pContentLength, int arg2) { 
                    System.out.println("�ļ���СΪ��" + pContentLength + ",��ǰ�Ѵ���" + pBytesRead); 
                     
                } 
            });*/  
             //����ϴ��ļ�������������  
            upload.setHeaderEncoding("UTF-8");   
            //3���ж��ύ�����������Ƿ����ϴ���������  
            if(!ServletFileUpload.isMultipartContent(request)){  
                //���մ�ͳ��ʽ��ȡ����  
                return;  
            }  
              
            //�����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB  
            upload.setFileSizeMax(1024*1024);  
            //�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB  
            upload.setSizeMax(1024*1024*10);  
              
            //  
             
              
            //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������  
            List<FileItem> list = upload.parseRequest(request);  
            for(FileItem item : list){  
                //���fileitem�з�װ������ͨ�����������  
                if(item.isFormField()){  
                    String name = item.getFieldName();  
                    //�����ͨ����������ݵ�������������  
                    String value = item.getString("UTF-8");  
//                    String value = item.getString("gbk");  
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");  
                    System.out.println(name + "=" + value);  
                }else{//���fileitem�з�װ�����ϴ��ļ�  
                    //�õ��ϴ����ļ����ƣ�  
                    String filename = item.getName();  
                    System.out.println(filename+"..");  
                    if(filename==null || filename.trim().equals("")){  
                        continue;  
                    }  
                    //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt  
                    //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������  
                    filename = filename.substring(filename.lastIndexOf("\\")+1);  
                    //�õ��ϴ��ļ�����չ��  
                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);  
                    //�����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�  
                    System.out.println("�ϴ����ļ�����չ���ǣ�"+fileExtName);  
                    //��ȡitem�е��ϴ��ļ���������  
                    InputStream in = item.getInputStream();  
                    //�õ��ļ����������  
                    String saveFilename = makeFileName(filename);  
                    //�õ��ļ��ı���Ŀ¼  
                    String realSavePath = makePath(saveFilename, savePath);  
                    //����һ���ļ������  
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);  
                    //����һ��������  
                    byte buffer[] = new byte[1024];  
                    //�ж��������е������Ƿ��Ѿ�����ı�ʶ  
                    int len = 0;  
                    StringBuffer sb = new StringBuffer();  
                    //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������  
                    while((len=in.read(buffer))>0){  
                        //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����  
//                      System.out.println(realSavePath);  
//                      System.out.println();  
                        sb.append(new String(buffer,0,len));  
//                        logger.info(sb.toString());  
//                        System.out.println(sb.toString());  
//                        sb.setLength(0);  
                        out.write(buffer, 0, len);  
                    }  
//                    System.out.println(sb.toString()+"-----");  
                    String ss = sb.toString();  
                      
//                    System.out.println(sb.);  
                    //�ر�������  
                    in.close();  
                    //�ر������  
                    out.close();  
                    //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�  
                    //item.delete();  
                    SaveData2DB saveData2DB = new SaveData2DB();  
                    //saveData2DB.save(realSavePath + "\\" + saveFilename);  
                    System.out.println("end");  
                      
                    message = "success";  
                }  
            }  
        }catch (FileUploadBase.FileSizeLimitExceededException e) {  
            e.printStackTrace();  
            message = "�����ļ��������ֵ������";  
            /*request.setAttribute("message", "�����ļ��������ֵ������");*/  
           /* request.getRequestDispatcher("/message.jsp").forward(request, response);*/  
            return;  
        }catch (FileUploadBase.SizeLimitExceededException e) {  
            e.printStackTrace();  
            message = "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������";  
            /*request.setAttribute("message", "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������");*/  
            /*request.getRequestDispatcher("/message.jsp").forward(request, response);*/  
            return;  
        }catch (Exception e) {  
            message= "�ļ��ϴ�ʧ�ܣ�";  
            e.printStackTrace();  
        }  
        /*request.setAttribute("message",message);*/  
        returnResultJson(response,message);  
        /*request.getRequestDispatcher("/message.jsp").forward(request, response);*/  
	}
	
	 private String makeFileName(String filename){  //2.jpg  
	        //Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���  
	        return UUID.randomUUID().toString() + "_" + filename;  
	    }  
	      
	       
	    private String makePath(String filename,String savePath){  
	        //�õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ  
	        int hashcode = filename.hashCode();  
	        int dir1 = hashcode&0xf;  //0--15  
	        int dir2 = (hashcode&0xf0)>>4;  //0-15  
	        //�����µı���Ŀ¼  
	        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5  
	        //File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼  
	        File file = new File(dir);  
	        //���Ŀ¼������  
	        if(!file.exists()){  
	            //����Ŀ¼  
	            file.mkdirs();  
	        }  
	        return dir;  
	    }  
	    private void returnResultJson(HttpServletResponse response,Object obj) {  
	        PrintWriter pw = null;  
	        try {  
	            pw = response.getWriter();  	        
	            JSONObject resultmessage = new JSONObject((Map<String, Object>) obj);
	            response.setCharacterEncoding("UTF-8");  
	            response.setContentType("application/json");  
	            response.setHeader("Cache-Control", "no-cache");  
	            pw.write(resultmessage.toString());  
	        } catch (Exception e) {  
	            pw.write("ϵͳ�쳣������ϵ����Ա");  
	        } finally {  
	            pw.flush();  
	            pw.close();  
	        }  
	    }  
	    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
