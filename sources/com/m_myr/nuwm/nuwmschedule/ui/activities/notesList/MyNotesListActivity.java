package com.m_myr.nuwm.nuwmschedule.ui.activities.notesList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.NotesRecyclerViewAdapter;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.ItemClick;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.notes.MyNotesActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MyNotesListActivity extends BaseToolbarActivity implements IMyNotesListView, ItemClick<Note> {
    private NotesRecyclerViewAdapter adapter;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private AppCompatTextView mToolbarTitle;
    MyNotesListPresenter presenter = new MyNotesListPresenter(this);
    private boolean grid = true;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_noteslist_activity);
        this.mToolbarTitle = (AppCompatTextView) findViewById(R.id.toolbar_title);
        this.mFab = (FloatingActionButton) findViewById(R.id.fab);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        attachToolbar();
        this.adapter = new NotesRecyclerViewAdapter(this, new ArrayList(), this);
        this.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        this.mRecyclerView.setAdapter(this.adapter);
        this.mFab.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.notesList.MyNotesListActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyNotesListActivity.this.m188x72acae3f(view);
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-m_myr-nuwm-nuwmschedule-ui-activities-notesList-MyNotesListActivity, reason: not valid java name */
    /* synthetic */ void m188x72acae3f(View view) {
        startActivityForResult(MyNotesActivity.getIntent(this), 53);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notelist, menu);
        return true;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.notesList.IMyNotesListView
    public void updateAll(List<Note> notes) {
        this.adapter.addAll(notes);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 54) {
            if (resultCode == 11) {
                this.adapter.update((Note) data.getSerializableExtra("note"), data.getIntExtra("position", -1));
            } else if (resultCode == 12) {
                this.adapter.remove((Note) data.getSerializableExtra("note"), data.getIntExtra("position", -1));
            }
        }
        if (requestCode == 53 && resultCode == 10) {
            this.adapter.add((Note) data.getSerializableExtra("note"));
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.ItemClick
    public void onClick(int position, Note note) {
        startActivityForResult(MyNotesActivity.getIntent(this, position, note), 54);
    }

    public void onChangeViewStyle(MenuItem item) {
        RecyclerView.LayoutManager linearLayoutManager;
        boolean z = this.grid;
        this.grid = !z;
        if (!z) {
            linearLayoutManager = new StaggeredGridLayoutManager(2, 1);
            item.setIcon(R.drawable.view_agenda_outline);
        } else {
            linearLayoutManager = new LinearLayoutManager(this);
            item.setIcon(R.drawable.view_dashboard_outline);
        }
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
