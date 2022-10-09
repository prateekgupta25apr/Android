package prateek_gupta.downloadfile;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the URL
        url="https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";

        // Initializing the Download Manager
        DownloadManager downloadManager = (DownloadManager)
                getApplication().getSystemService(Context.DOWNLOAD_SERVICE);

        // Setting OnCLickListener for the download button
        findViewById(R.id.downloadFile).setOnClickListener(view -> {
            // Creating URI for the URL
            Uri uri = Uri.parse(url);

            // Initializing download request
            DownloadManager.Request request = new DownloadManager.Request(uri);

            // Setting notification visibility for downloading the file
            request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // Setting title while downloading the file
            request.setTitle("dummy.pdf");

            // Setting description while downloading the file
            request.setDescription("Downloading...");

            // Setting directory for saving the downloaded file
            request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,"dummy.pdf");

            // Enqueuing the download request
            downloadManager.enqueue(request);

            // Displaying a toast message
            Toast.makeText(getApplicationContext(),"Downloading...",Toast.LENGTH_LONG)
                    .show();
        });

        // Registering a broadcast receiver
        registerReceiver(new BroadcastReceiver() {
            @SuppressLint("Range")
            @Override
            public void onReceive(Context context, Intent intent) {
                // Checking action for the received broadcast(object of Intent)
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())){
                    // Fetching device level unique id of the downloaded file
                    long downloadId = intent.getLongExtra(
                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);

                    // Creating a query to query the downloaded files
                    DownloadManager.Query query = new DownloadManager.Query();

                    // Setting the downloaded files ids to query for
                    query.setFilterById(downloadId);

                    // Executing the query to fetch details of the downloaded files
                    // whose ids are set
                    Cursor cursor = downloadManager.query(query);

                    // Moving the cursor to first record
                    cursor.moveToFirst();

                    // Fetching path for the downloaded file
                    String filePath=Uri.parse(cursor.getString(cursor.getColumnIndex(
                            DownloadManager.COLUMN_LOCAL_URI))).getPath();

                    // Generating ContentURI for the downloaded file
                    Uri contentURI=FileProvider.getUriForFile(getApplicationContext(),
                            "prateek_gupta",new File(filePath));

                    // Creating intent to share the file with other apps
                    Intent fileIntent=new Intent(Intent.ACTION_VIEW);

                    // Fetching data type for the downloaded file
                    String dataType=MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                            MimeTypeMap.getFileExtensionFromUrl(url));

                    // Setting the data and data type for the intent
                    fileIntent.setDataAndType(contentURI, dataType);

                    // Setting permission for the intent to be shared with other apps
                    fileIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    // Opening the downloaded file
                    startActivity(fileIntent);
                }
            }
        },new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }
}