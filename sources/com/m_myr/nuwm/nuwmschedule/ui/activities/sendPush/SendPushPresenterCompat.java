package com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush;

import com.m_myr.nuwm.nuwmschedule.data.models.Document;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;
import com.m_myr.nuwm.nuwmschedule.domain.LegacyRepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.Topics;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class SendPushPresenterCompat extends LegacyRepositoryPresenter<SendPushOwner> {
    private Lesson attachLesson;
    private ArrayList<Document> documents;
    private String imageHeader;
    private int messageColor;
    private String text;
    private String title;

    public SendPushPresenterCompat(SendPushOwner view) {
        super(view);
        this.documents = new ArrayList<>();
        this.messageColor = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(SendPushOwner view) {
        Serializable serializableExtra = view.getIntent().getSerializableExtra("lesson");
        if (serializableExtra != null) {
            Lesson lesson = (Lesson) serializableExtra;
            this.attachLesson = lesson;
            view.attachLesson(lesson);
        }
    }

    public void selectColor(int color) {
        this.messageColor = color;
    }

    public void setImage(String url) {
        this.imageHeader = url;
    }

    public void addFile(UploadResponse result) {
        this.documents.add(new Document(result));
    }

    public void setText(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public void onSendClick() {
        String sb;
        if (Utils.StringUtils.isBlank(this.title) || Utils.StringUtils.isBlank(this.text)) {
            SendPushOwner sendPushOwner = (SendPushOwner) this.view;
            StringBuilder sb2 = new StringBuilder("Додайте ");
            if (Utils.StringUtils.isBlank(this.title)) {
                sb = "заголовок";
            } else {
                StringBuilder sb3 = new StringBuilder("текст ");
                sb3.append(Utils.StringUtils.isBlank(this.title) ? "" : " і текст");
                sb3.append(" повідомлення");
                sb = sb3.toString();
            }
            sb2.append(sb);
            sendPushOwner.showInfo(sb2.toString());
            return;
        }
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList(Arrays.asList(this.attachLesson.getAttendees().split(",")));
        getRepository().call(APIMethod.getTopics()).async(new APIOldObjectListener<Topics>(Topics.class) { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushPresenterCompat.1
            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                ((SendPushOwner) SendPushPresenterCompat.this.view).showInfo("Не вдалося отримати список тем");
                ((SendPushOwner) SendPushPresenterCompat.this.view).showAlertRecipients(arrayList2, arrayList);
            }

            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            public void onSuccessData(Topics data) {
                ((SendPushOwner) SendPushPresenterCompat.this.view).showAlertRecipients(arrayList2, data.getTopics());
            }
        });
    }
}
