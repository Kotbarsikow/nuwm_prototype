package com.m_myr.nuwm.nuwmschedule.ui.activities.notes;

import android.util.Log;
import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import com.m_myr.nuwm.nuwmschedule.data.repositories.RoomRepository;
import com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter;
import io.reactivex.functions.Action;

/* loaded from: classes2.dex */
public class MyNotesPresenter extends LifecyclePresenter<IMyNotesView> {
    private boolean change;
    private boolean edit;
    private Note note;
    private int position;
    boolean remove;
    RoomRepository roomRepository;

    public MyNotesPresenter(IMyNotesView view) {
        super(view);
        this.roomRepository = new RoomRepository();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(IMyNotesView view) {
        if (view.getIntent().getBooleanExtra("new", true)) {
            this.note = new Note();
            this.edit = false;
        } else {
            this.note = (Note) view.getIntent().getSerializableExtra("note");
            this.position = view.getIntent().getIntExtra("position", -1);
            this.edit = true;
            view.setData(this.note);
        }
        view.attachListener();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.BasePresenter
    public void detachView() {
        if (this.remove) {
            return;
        }
        this.note.setTimeEdit(System.currentTimeMillis());
        if (this.change) {
            if (this.edit) {
                this.roomRepository.call(new Action() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.notes.MyNotesPresenter$$ExternalSyntheticLambda0
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        MyNotesPresenter.this.m185x7290fb77();
                    }
                });
            } else {
                this.roomRepository.call(new Action() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.notes.MyNotesPresenter$$ExternalSyntheticLambda1
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                        MyNotesPresenter.this.m186xdcc08396();
                    }
                });
            }
            super.detachView();
        }
    }

    /* renamed from: lambda$detachView$0$com-m_myr-nuwm-nuwmschedule-ui-activities-notes-MyNotesPresenter, reason: not valid java name */
    /* synthetic */ void m185x7290fb77() throws Exception {
        this.roomRepository.notesDao().update(this.note);
    }

    /* renamed from: lambda$detachView$1$com-m_myr-nuwm-nuwmschedule-ui-activities-notes-MyNotesPresenter, reason: not valid java name */
    /* synthetic */ void m186xdcc08396() throws Exception {
        this.roomRepository.notesDao().insert(this.note);
    }

    public void updateText(String title, String body) {
        Log.e("updateText", title + body);
        this.note.setTitle(title);
        this.note.setBodyHtml(body);
        wasChange();
    }

    public int getColorNote() {
        return this.note.getColorNote();
    }

    public void setColorNote(int color) {
        this.note.setColorNote(color);
        wasChange();
    }

    public void onBackPressed() {
        if (this.change) {
            if (this.edit) {
                ((IMyNotesView) this.view).setChangeResult(this.position, this.note);
            } else {
                ((IMyNotesView) this.view).setInsertResult(this.note);
            }
        }
    }

    private void wasChange() {
        this.change = true;
    }

    /* renamed from: lambda$onClickDelete$2$com-m_myr-nuwm-nuwmschedule-ui-activities-notes-MyNotesPresenter, reason: not valid java name */
    /* synthetic */ void m187xedcc0673() throws Exception {
        this.roomRepository.notesDao().delete(this.note);
    }

    public void onClickDelete() {
        this.roomRepository.call(new Action() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.notes.MyNotesPresenter$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Action
            public final void run() {
                MyNotesPresenter.this.m187xedcc0673();
            }
        });
        ((IMyNotesView) this.view).setRemoveResult(this.position, this.note);
        ((IMyNotesView) this.view).finish();
        this.remove = true;
    }
}
