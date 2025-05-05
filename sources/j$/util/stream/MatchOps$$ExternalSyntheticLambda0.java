package j$.util.stream;

import java.util.function.Supplier;

/* loaded from: classes4.dex */
public final /* synthetic */ class MatchOps$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MatchOps$MatchKind f$0;

    public /* synthetic */ MatchOps$$ExternalSyntheticLambda0(MatchOps$MatchKind matchOps$MatchKind, int i) {
        this.$r8$classId = i;
        this.f$0 = matchOps$MatchKind;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.$r8$classId) {
            case 0:
                return new MatchOps$3MatchSink(this.f$0);
            case 1:
                return new MatchOps$2MatchSink(this.f$0);
            default:
                return new MatchOps$4MatchSink(this.f$0);
        }
    }
}
