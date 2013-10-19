package cl.telematica.tasks3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyncActivity extends Activity {
	
	private ProgressBar progressBar;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		button = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new MyAsyncTask().execute();
			}
			
		});
	}
	
	private void task()
	{
	    try {
	        Thread.sleep(1000);
	    } catch(InterruptedException e) {}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.async, menu);
		return true;
	}
	
	private class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
		 
	    @Override
	    protected Boolean doInBackground(Void... params) {
	 
	        for(int i=1; i<=10; i++) {
	            task();
	 
	            publishProgress(i*10);
	 
	            if(isCancelled())
	                break;
	        }
	 
	        return true;
	    }
	 
	    @Override
	    protected void onProgressUpdate(Integer... values) {
	        int progreso = values[0].intValue();
	 
	        progressBar.setProgress(progreso);
	    }
	 
	    @Override
	    protected void onPreExecute() {
	    	progressBar.setMax(100);
	    	progressBar.setProgress(0);
	    }
	 
	    @Override
	    protected void onPostExecute(Boolean result) {
	        if(result)
	            Toast.makeText(AsyncActivity.this, "Tarea finalizada!",
	                    Toast.LENGTH_SHORT).show();
	    }
	 
	    @Override
	    protected void onCancelled() {
	        Toast.makeText(AsyncActivity.this, "Tarea cancelada!",
	                Toast.LENGTH_SHORT).show();
	    }
	}

}
