package prateek_gupta.downloadfile

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the URL
        url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"

        // Initializing the Download Manager
        val downloadManager = application.getSystemService(DOWNLOAD_SERVICE) as
                DownloadManager

        // Setting OnCLickListener for the download button
        findViewById<Button>(R.id.downloadFile).setOnClickListener {
            // Creating URI for the URL
            val uri = Uri.parse(url)

            // Initializing download request
            val request = DownloadManager.Request(uri)

            // Setting notification visibility for downloading the file
            request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )

            // Setting title while downloading the file
            request.setTitle("dummy.pdf")

            // Setting description while downloading the file
            request.setDescription("Downloading...")

            // Setting directory for saving the downloaded file
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS, "dummy.pdf"
            )

            // Enqueuing the download request
            downloadManager.enqueue(request)

            // Displaying a toast message
            Toast.makeText(applicationContext, "Downloading...", Toast.LENGTH_LONG)
                .show()
        }

        // Registering a broadcast receiver

        // Registering a broadcast receiver
        registerReceiver(object : BroadcastReceiver() {
            @SuppressLint("Range")
            override fun onReceive(context: Context, intent: Intent) {
                // Checking action for the received broadcast(object of Intent)
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE == intent.action) {
                    // Fetching device level unique id of the downloaded file
                    val downloadId = intent.getLongExtra(
                        DownloadManager.EXTRA_DOWNLOAD_ID, 0
                    )

                    // Creating a query to query the downloaded files
                    val query = DownloadManager.Query()

                    // Setting the downloaded files ids to query for
                    query.setFilterById(downloadId)

                    // Executing the query to fetch details of the downloaded files
                    // whose ids are set
                    val cursor = downloadManager.query(query)

                    // Moving the cursor to first record
                    cursor.moveToFirst()

                    // Fetching path for the downloaded file
                    val filePath = Uri.parse(cursor.getString(
                            cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))).path

                    // Generating ContentURI for the downloaded file
                    val contentURI = FileProvider.getUriForFile(
                        applicationContext,
                        "prateek_gupta", File(filePath!!)
                    )

                    // Creating intent to share the file with other apps
                    val fileIntent = Intent(Intent.ACTION_VIEW)

                    // Fetching data type for the downloaded file
                    val dataType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                        MimeTypeMap.getFileExtensionFromUrl(url)
                    )

                    // Setting the data and data type for the intent
                    fileIntent.setDataAndType(contentURI, dataType)

                    // Setting permission for the intent to be shared with other apps
                    fileIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

                    // Opening the downloaded file
                    startActivity(fileIntent)
                }
            }
        }, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}