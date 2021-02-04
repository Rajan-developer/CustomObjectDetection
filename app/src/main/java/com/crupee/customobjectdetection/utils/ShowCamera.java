package com.crupee.customobjectdetection.utils;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceHolder holder;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Camera.Parameters params = camera.getParameters();

        //change the orientaion of the camera
        /*if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        } else {
            params.set("orientaion", "landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }*/

        params.set("orientaion", "landscape");
        camera.setDisplayOrientation(180);
        params.setRotation(180);

        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        camera.stopPreview();
        camera.startPreview();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
    }
}
