package com.wx.waterripple;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.filters.TouchRippleFilter;
import rajawali.lights.DirectionalLight;
import rajawali.materials.SimpleMaterial;
import rajawali.primitives.Plane;
import rajawali.renderer.PostProcessingRenderer.PostProcessingQuality;
import rajawali.renderer.RajawaliRenderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RipplesRenderer extends RajawaliRenderer {
    private TouchRippleFilter mFilter;
    private long frameCount;
    private final int QUAD_SEGMENTS = 40;

    public RipplesRenderer(Context context) {
        super(context);
        setFrameRate(60);
    }

    @Override
    protected void initScene() {
        mCamera.setPosition(0, 0, -10);

        DirectionalLight light = new DirectionalLight(0, 0, 1);
        light.setPower(1f);

        SimpleMaterial planeMat = new SimpleMaterial();
        Bitmap texture = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.image_0);
        planeMat.addTexture(mTextureManager.addTexture(texture));

        Plane plane = new Plane(4, 4, 1, 1);
        plane.setRotZ(-90);
        plane.setScale(3.7f);
        plane.setMaterial(planeMat);
        addChild(plane);

        mFilter = new TouchRippleFilter();
        mFilter.setRippleSize(62);
        mPostProcessingRenderer.setQuadSegments(QUAD_SEGMENTS);
        mPostProcessingRenderer.setQuality(PostProcessingQuality.LOW);
        addPostProcessingFilter(mFilter);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        mFilter.setScreenSize(mViewportWidth, mViewportHeight);
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {
        super.onDrawFrame(glUnused);
        mFilter.setTime((float) frameCount++ * .05f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
        mFilter.setScreenSize(width, height);
    }

    public void setTouch(float x, float y) {
        mFilter.addTouch(x, y, frameCount * .05f);
    }
}
