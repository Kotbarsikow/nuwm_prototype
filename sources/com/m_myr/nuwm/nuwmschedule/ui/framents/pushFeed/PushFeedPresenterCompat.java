package com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PostMessage;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PushFeedPresenterCompat extends RepositoryPresenter<PushFeedOwner> {
    public PushFeedPresenterCompat(PushFeedOwner view) {
        super(view, true);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(PushFeedOwner view) {
        loadData();
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedPresenterCompat$1 */
    class AnonymousClass1 extends APIObjectListener<ArrayList<PostMessage>> {
        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
        }

        AnonymousClass1() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(ArrayList<PostMessage> data) {
            ((PushFeedOwner) PushFeedPresenterCompat.this.view).setData(data);
        }

        /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedPresenterCompat$1$1 */
        class C00431 extends TypeToken<ArrayList<PostMessage>> {
            C00431() {
            }
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener, com.m_myr.nuwm.nuwmschedule.domain.interfaces.GsonGetter
        public Gson getGson() {
            Type type = new TypeToken<ArrayList<PostMessage>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedPresenterCompat.1.1
                C00431() {
                }
            }.getType();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(type, new JsonPushResponseDeserialize());
            return gsonBuilder.create();
        }
    }

    private void loadData() {
        getRepository().call(APIMethods.getPush(AppDataManager.getInstance().getTopics())).async(new APIObjectListener<ArrayList<PostMessage>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedPresenterCompat.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
            }

            AnonymousClass1() {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ArrayList<PostMessage> data) {
                ((PushFeedOwner) PushFeedPresenterCompat.this.view).setData(data);
            }

            /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedPresenterCompat$1$1 */
            class C00431 extends TypeToken<ArrayList<PostMessage>> {
                C00431() {
                }
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener, com.m_myr.nuwm.nuwmschedule.domain.interfaces.GsonGetter
            public Gson getGson() {
                Type type = new TypeToken<ArrayList<PostMessage>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedPresenterCompat.1.1
                    C00431() {
                    }
                }.getType();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(type, new JsonPushResponseDeserialize());
                return gsonBuilder.create();
            }
        });
    }

    public void reload() {
        getRepository().cancelTask();
        loadData();
    }
}
