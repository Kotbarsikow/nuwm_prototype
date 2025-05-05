package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class LayoutRepositoryDocumentBinding implements ViewBinding {
    public final TextView abstracts;
    public final AppCompatTextView appCompatTextView;
    public final Barrier barrier;
    public final ConstraintLayout constraintContentActivity;
    public final LinearLayout creators;
    public final TextView dateYear;
    public final MaterialButton downloadButton;
    public final LinearLayout downloading;
    public final TextView fileSize;
    public final Guideline guidelineA;
    public final Guideline guidelineB;
    public final MaterialCardView imageCard;
    public final ImageView imageView4;
    public final RecyclerView images;
    public final TextView keywords;
    public final LinearLayout linearLayout5;
    public final LinearLayout linearLayout6;
    public final LinearLayout linearLayout7;
    public final TextView loadCounter;
    public final MaterialButton openFile;
    public final Group previewGroups;
    public final ProgressBar progressHorizontal;
    private final ConstraintLayout rootView;
    public final TextView shufr;
    public final TextView textView7;
    public final TextView textView9;
    public final AppCompatTextView title;
    public final ImageView typeFileImage;
    public final TextView typeFileName;
    public final View view11;
    public final View view2;
    public final View view3;
    public final View view8;
    public final View view9;

    private LayoutRepositoryDocumentBinding(ConstraintLayout rootView, TextView abstracts, AppCompatTextView appCompatTextView, Barrier barrier, ConstraintLayout constraintContentActivity, LinearLayout creators, TextView dateYear, MaterialButton downloadButton, LinearLayout downloading, TextView fileSize, Guideline guidelineA, Guideline guidelineB, MaterialCardView imageCard, ImageView imageView4, RecyclerView images, TextView keywords, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, TextView loadCounter, MaterialButton openFile, Group previewGroups, ProgressBar progressHorizontal, TextView shufr, TextView textView7, TextView textView9, AppCompatTextView title, ImageView typeFileImage, TextView typeFileName, View view11, View view2, View view3, View view8, View view9) {
        this.rootView = rootView;
        this.abstracts = abstracts;
        this.appCompatTextView = appCompatTextView;
        this.barrier = barrier;
        this.constraintContentActivity = constraintContentActivity;
        this.creators = creators;
        this.dateYear = dateYear;
        this.downloadButton = downloadButton;
        this.downloading = downloading;
        this.fileSize = fileSize;
        this.guidelineA = guidelineA;
        this.guidelineB = guidelineB;
        this.imageCard = imageCard;
        this.imageView4 = imageView4;
        this.images = images;
        this.keywords = keywords;
        this.linearLayout5 = linearLayout5;
        this.linearLayout6 = linearLayout6;
        this.linearLayout7 = linearLayout7;
        this.loadCounter = loadCounter;
        this.openFile = openFile;
        this.previewGroups = previewGroups;
        this.progressHorizontal = progressHorizontal;
        this.shufr = shufr;
        this.textView7 = textView7;
        this.textView9 = textView9;
        this.title = title;
        this.typeFileImage = typeFileImage;
        this.typeFileName = typeFileName;
        this.view11 = view11;
        this.view2 = view2;
        this.view3 = view3;
        this.view8 = view8;
        this.view9 = view9;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutRepositoryDocumentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutRepositoryDocumentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.layout_repository_document, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutRepositoryDocumentBinding bind(View rootView) {
        int i = R.id.abstracts;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.abstracts);
        if (textView != null) {
            i = R.id.appCompatTextView;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.appCompatTextView);
            if (appCompatTextView != null) {
                i = R.id.barrier;
                Barrier barrier = (Barrier) ViewBindings.findChildViewById(rootView, R.id.barrier);
                if (barrier != null) {
                    ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                    i = R.id.creators;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.creators);
                    if (linearLayout != null) {
                        i = R.id.date_year;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.date_year);
                        if (textView2 != null) {
                            i = R.id.downloadButton;
                            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.downloadButton);
                            if (materialButton != null) {
                                i = R.id.downloading;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.downloading);
                                if (linearLayout2 != null) {
                                    i = R.id.fileSize;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.fileSize);
                                    if (textView3 != null) {
                                        i = R.id.guidelineA;
                                        Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guidelineA);
                                        if (guideline != null) {
                                            i = R.id.guidelineB;
                                            Guideline guideline2 = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guidelineB);
                                            if (guideline2 != null) {
                                                i = R.id.imageCard;
                                                MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(rootView, R.id.imageCard);
                                                if (materialCardView != null) {
                                                    i = R.id.imageView4;
                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView4);
                                                    if (imageView != null) {
                                                        i = R.id.images;
                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.images);
                                                        if (recyclerView != null) {
                                                            i = R.id.keywords;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.keywords);
                                                            if (textView4 != null) {
                                                                i = R.id.linearLayout5;
                                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.linearLayout5);
                                                                if (linearLayout3 != null) {
                                                                    i = R.id.linearLayout6;
                                                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.linearLayout6);
                                                                    if (linearLayout4 != null) {
                                                                        i = R.id.linearLayout7;
                                                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.linearLayout7);
                                                                        if (linearLayout5 != null) {
                                                                            i = R.id.loadCounter;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.loadCounter);
                                                                            if (textView5 != null) {
                                                                                i = R.id.openFile;
                                                                                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.openFile);
                                                                                if (materialButton2 != null) {
                                                                                    i = R.id.preview_groups;
                                                                                    Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.preview_groups);
                                                                                    if (group != null) {
                                                                                        i = R.id.progress_horizontal;
                                                                                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progress_horizontal);
                                                                                        if (progressBar != null) {
                                                                                            i = R.id.shufr;
                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.shufr);
                                                                                            if (textView6 != null) {
                                                                                                i = R.id.textView7;
                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView7);
                                                                                                if (textView7 != null) {
                                                                                                    i = R.id.textView9;
                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.textView9);
                                                                                                    if (textView8 != null) {
                                                                                                        i = R.id.title;
                                                                                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.title);
                                                                                                        if (appCompatTextView2 != null) {
                                                                                                            i = R.id.typeFileImage;
                                                                                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.typeFileImage);
                                                                                                            if (imageView2 != null) {
                                                                                                                i = R.id.typeFileName;
                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.typeFileName);
                                                                                                                if (textView9 != null) {
                                                                                                                    i = R.id.view11;
                                                                                                                    View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.view11);
                                                                                                                    if (findChildViewById != null) {
                                                                                                                        i = R.id.view2;
                                                                                                                        View findChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.view2);
                                                                                                                        if (findChildViewById2 != null) {
                                                                                                                            i = R.id.view3;
                                                                                                                            View findChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.view3);
                                                                                                                            if (findChildViewById3 != null) {
                                                                                                                                i = R.id.view8;
                                                                                                                                View findChildViewById4 = ViewBindings.findChildViewById(rootView, R.id.view8);
                                                                                                                                if (findChildViewById4 != null) {
                                                                                                                                    i = R.id.view9;
                                                                                                                                    View findChildViewById5 = ViewBindings.findChildViewById(rootView, R.id.view9);
                                                                                                                                    if (findChildViewById5 != null) {
                                                                                                                                        return new LayoutRepositoryDocumentBinding(constraintLayout, textView, appCompatTextView, barrier, constraintLayout, linearLayout, textView2, materialButton, linearLayout2, textView3, guideline, guideline2, materialCardView, imageView, recyclerView, textView4, linearLayout3, linearLayout4, linearLayout5, textView5, materialButton2, group, progressBar, textView6, textView7, textView8, appCompatTextView2, imageView2, textView9, findChildViewById, findChildViewById2, findChildViewById3, findChildViewById4, findChildViewById5);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
