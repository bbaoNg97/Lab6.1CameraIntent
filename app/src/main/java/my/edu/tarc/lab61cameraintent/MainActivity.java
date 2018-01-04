package my.edu.tarc.lab61cameraintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE=1;//request code,can put any number
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView=(ImageView)findViewById(R.id.imageViewthumbnail);
        Button buttonCamera=(Button)findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureEvent();
            }
        });
    }

    //must have this to call back the result on startActivityforResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            //use bundle to get data
            Bundle extras=data.getExtras();
            //obtain a thumbnail image fro 'data'
            //data is always call data, fiexed alr
            //bitmap somthing like compress the'data',so the result is in bitmap mode
            Bitmap imageBitmap=(Bitmap)extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private void dispatchTakePictureEvent() {
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //RESOLVEaCTIVITY method determine the best action,ask pakageManager for any app can handle action_image_capture?
        //to perforn for a given Intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){

            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }
}
