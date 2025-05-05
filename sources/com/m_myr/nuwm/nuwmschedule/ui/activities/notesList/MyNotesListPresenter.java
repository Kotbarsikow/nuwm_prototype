package com.m_myr.nuwm.nuwmschedule.ui.activities.notesList;

import android.util.Log;
import com.m_myr.nuwm.nuwmschedule.data.database.DataCallback;
import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import com.m_myr.nuwm.nuwmschedule.data.repositories.RoomRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public class MyNotesListPresenter extends LifecyclePresenter<IMyNotesListView> {
    RoomRepository roomRepository;

    public MyNotesListPresenter(IMyNotesListView view) {
        super(view);
        this.roomRepository = new RoomRepository();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final IMyNotesListView view) {
        this.roomRepository.call(new Callable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.notesList.MyNotesListPresenter$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List all;
                all = App.getInstance().getDatabase().notesDao().getAll();
                return all;
            }
        }, new DataCallback<List<Note>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.notesList.MyNotesListPresenter.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.m_myr.nuwm.nuwmschedule.data.database.DataCallback
            public void onSuccess(List<Note> notes) {
                view.updateAll(notes);
                Log.e("MyNotesListPresenter", "onSuccess" + notes.size());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.data.database.DataCallback
            protected void onError(Throwable throwable) {
                Log.e("MyNotesListPresenter", "onError");
                throwable.printStackTrace();
            }
        });
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.BasePresenter
    public void detachView() {
        super.detachView();
        this.roomRepository.unsubscribe();
    }
}
