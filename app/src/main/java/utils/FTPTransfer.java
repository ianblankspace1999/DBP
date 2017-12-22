package utils;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;

import android.os.Environment;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class FTPTransfer {

    /*********  work only for Dedicated IP ***********/
    private String FTP_HOST = "125.5.213.20:19002";

    /*********  FTP USERNAME ***********/
    private String FTP_USER = "devapk";

    /*********  FTP PASSWORD ***********/
    private String FTP_PASS = "devapk123";
//    private String FTP_PASS = "edEuTY9rbgkXcFyX";

    private Context context;

    private AppCompatActivity appComaptActivity;

//    public FTPTransfer(String FTP_HOST, String FTP_USER, String FTP_PASS, Context context) {
//        this.FTP_HOST = FTP_HOST;
//        this.FTP_USER = FTP_USER;
//        this.FTP_PASS = FTP_PASS;
//        this.context = context;
//    }


    public FTPTransfer(Context context) {
        this.context = context;
    }


    public void downloadStart() throws MalformedURLException {
         ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("sample");
        progressDialog.setCancelable(false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String ftpPath = "ftp://125.5.213.20:19002";
        URL url = new URL(ftpPath);
        String host = url.getHost();
        FTPClient client = new FTPClient();
        try {
            client.setAutoNoopTimeout(3000);
            client.connect(host, 19002);
            client.login(FTP_USER, FTP_PASS);
            client.setType(FTPClient.TYPE_BINARY);
            client.setPassive(true);
            client.noop();
            FTPFile[] list = client.list(url.getPath() + "1.1/");
            for (FTPFile f : list) {
                // Instead of printing out the file download it. See
                // http://www.sauronsoftware.it/projects/ftp4j/manual.php#14
                System.out.println(f);
                Log.i("gumana", "gumana: name " + f.getName());
                Log.i("gumana", "gumana: link: " + f.getLink());
                Log.i("gumana", "gumana: type: " + f.getType());
                Log.i("gumana", "gumana: date: " + f.getModifiedDate());
//                client.download("MRcos.apk");
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), f.getName());
                startActualFileDownload("1.1/" + f.getName(), f.getName(), client, file);
            }
        } catch (FTPIllegalReplyException e) {
            e.printStackTrace();
            Log.i("gumana", "errors1: " + e.toString());
        } catch (FTPException e) {
            e.printStackTrace();
            Log.i("gumana", "errors2: " + e.toString());
        } catch (FTPDataTransferException e) {
            e.printStackTrace();
            Log.i("gumana", "errors3: " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("gumana", "errors4: " + e.toString());
        } catch (FTPListParseException e) {
            e.printStackTrace();
            Log.i("gumana", "errors5: " + e.toString());
        } catch (FTPAbortedException e) {
            e.printStackTrace();
            Log.i("gumana", "errors6: " + e.toString());
        }
    }

    private void startActualFileDownload(final String ftpFileDonwnloadPath, final String fileName, FTPClient ftp, File sdcardFileDownloadPath) {
        try {
            ftp.download(ftpFileDonwnloadPath, sdcardFileDownloadPath,
                    new FTPDataTransferListener() {

                        public void transferred(int arg0) {
                            //Log.v("log_tag", "This is for tranfer");
//                            appComaptActivity.runOnUiThread(new Runnable() {
//                                public void run() {
//                                    //btn.setVisibility(View.GONE);
//                                    // Transfer started
//                                    Toast.makeText(context, " Upload Started ...", Toast.LENGTH_SHORT).show();
//                                }
//                            });

//                            new Thread() {
//                                public void run() {
//                                        try {
//                                            appComaptActivity.runOnUiThread(new Runnable() {
//
//                                                @Override
//                                                public void run() {
//                                                    Toast.makeText(context, " Upload Started ...", Toast.LENGTH_SHORT).show();
//                                                }
//                                            });
//                                            Thread.sleep(300);
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                            }.start();

//                            Toast.makeText(context, " transferred ..." + arg0, Toast.LENGTH_SHORT).show();
                        }

                        public void started() {

//                            appComaptActivity.runOnUiThread(new Runnable() {
//                                public void run() {
//                                    //btn.setVisibility(View.GONE);
//                                    // Transfer started
//                                    Toast.makeText(context, " Upload Started ...", Toast.LENGTH_SHORT).show();
//                                }
////                            });
//                            Thread thread = new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(context, " Upload Started ...", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            thread.run();
                            // TODO Auto-generated method stub
//                            Toast.makeText(context, " Upload Started ...", Toast.LENGTH_SHORT).show();
                            //Log.v("log_tag", "This is for started");
                        }

                        public void failed() {
//                            Toast.makeText(context, "  failed ...", Toast.LENGTH_SHORT).show();
                            System.out.println(" failed ...");
                        }

                        public void completed() {
//                            Toast.makeText(context, " completed ...", Toast.LENGTH_SHORT).show();
                            //Log.v("log_tag", "This is for completed");
                            Intent intent = new Intent(Intent.ACTION_VIEW);

                            Log.i("eto yun", "eto: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + ftpFileDonwnloadPath);
                            Log.i("eto yun", "eto: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Mrcos.apk");
                            intent.setDataAndType(Uri.fromFile(new File
                                    (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName)), "application/vnd.android.package-archive");
                            context.startActivity(intent);

                        }

                        public void aborted() {
//                            Toast.makeText(context, " transfer aborted,please try again...", Toast.LENGTH_SHORT).show();
                            //Log.v("log_tag", "This is for aborted");

                        }
                    });
        } catch (Exception e) {
            e.toString();
            Log.i("tag", "errors9: " + e.toString());
        }
    }


    public boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }


    /*******  Used to file upload and show progress  **********/

    public class MyTransferListener implements FTPDataTransferListener {
        //        LongOperation task = new LongOperation()
        public void started() {

            // Transfer started
            Toast.makeText(context, " Upload Started ...", Toast.LENGTH_SHORT).show();
            //System.out.println(" Upload Started ...");
        }

        public void transferred(int length) {

            // Yet other length bytes has been transferred since the last time this
            // method was called
            Toast.makeText(context, " transferred ..." + length, Toast.LENGTH_SHORT).show();
            //System.out.println(" transferred ..." + length);
        }

        public void completed() {

            // Transfer completed

            Toast.makeText(context, " completed ...", Toast.LENGTH_SHORT).show();
            //System.out.println(" completed ..." );
        }

        public void aborted() {

            // Transfer aborted
            Toast.makeText(context, " transfer aborted,please try again...", Toast.LENGTH_SHORT).show();
            //System.out.println(" aborted ..." );
        }

        public void failed() {

            // Transfer failed
            System.out.println(" failed ...");
        }


    }
}
