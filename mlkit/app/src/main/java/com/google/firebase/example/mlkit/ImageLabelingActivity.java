package com.google.firebase.example.mlkit;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabel;
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabelDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector;
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetectorOptions;

import java.util.List;

public class ImageLabelingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void labelImages(FirebaseVisionImage image) {
        // [START set_detector_options]
        FirebaseVisionLabelDetectorOptions options =
                new FirebaseVisionLabelDetectorOptions.Builder()
                        .setConfidenceThreshold(0.8f)
                        .build();
        // [END set_detector_options]

        // [START get_detector_default]
        FirebaseVisionLabelDetector detector = FirebaseVision.getInstance()
                .getVisionLabelDetector();
        // [END get_detector_default]

        /*
        // [START get_detector_options]
        // Or, to set the minimum confidence required:
        FirebaseVisionLabelDetector detector = FirebaseVision.getInstance()
                .getVisionLabelDetector(options);
        // [END get_detector_options]
        */

        // [START run_detector]
        Task<List<FirebaseVisionLabel>> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<FirebaseVisionLabel>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionLabel> labels) {
                                        // Task completed successfully
                                        // [START_EXCLUDE]
                                        // [START get_labels]
                                        for (FirebaseVisionLabel label: labels) {
                                            String text = label.getLabel();
                                            String entityId = label.getEntityId();
                                            float confidence = label.getConfidence();
                                        }
                                        // [END get_labels]
                                        // [END_EXCLUDE]
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });
        // [END run_detector]
    }

    private void labelImagesCloud(FirebaseVisionImage image) {
        // [START set_detector_options_cloud]
        FirebaseVisionCloudDetectorOptions options = new FirebaseVisionCloudDetectorOptions.Builder()
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .setMaxResults(30)
                .build();
        // [END set_detector_options_cloud]

        // [START get_detector_cloud]
        FirebaseVisionCloudLabelDetector detector = FirebaseVision.getInstance()
                .getVisionCloudLabelDetector();
        // Or, to change the default settings:
        // FirebaseVisionCloudLabelDetector detector = FirebaseVision.getInstance()
        //         .getVisionCloudLabelDetector(options);
        // [END get_detector_cloud]

        // [START run_detector_cloud]
        Task<List<FirebaseVisionCloudLabel>> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<FirebaseVisionCloudLabel>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionCloudLabel> labels) {
                                        // Task completed successfully
                                        // [START_EXCLUDE]
                                        // [START get_labels_cloud]
                                        for (FirebaseVisionCloudLabel label: labels) {
                                            String text = label.getLabel();
                                            String entityId = label.getEntityId();
                                            float confidence = label.getConfidence();
                                        }
                                        // [END get_labels_cloud]
                                        // [END_EXCLUDE]
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });
        // [END run_detector_cloud]
    }
}
