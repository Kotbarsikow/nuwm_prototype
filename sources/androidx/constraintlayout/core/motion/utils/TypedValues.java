package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public interface TypedValues {
    public static final int BOOLEAN_MASK = 1;
    public static final int FLOAT_MASK = 4;
    public static final int INT_MASK = 2;
    public static final int STRING_MASK = 8;
    public static final String S_CUSTOM = "CUSTOM";
    public static final int TYPE_FRAME_POSITION = 100;
    public static final int TYPE_TARGET = 101;

    public interface OnSwipe {
        public static final String AUTOCOMPLETE_MODE = "autocompletemode";
        public static final String DRAG_DIRECTION = "dragdirection";
        public static final String DRAG_SCALE = "dragscale";
        public static final String DRAG_THRESHOLD = "dragthreshold";
        public static final String LIMIT_BOUNDS_TO = "limitboundsto";
        public static final String MAX_ACCELERATION = "maxacceleration";
        public static final String MAX_VELOCITY = "maxvelocity";
        public static final String MOVE_WHEN_SCROLLAT_TOP = "movewhenscrollattop";
        public static final String NESTED_SCROLL_FLAGS = "nestedscrollflags";
        public static final String ON_TOUCH_UP = "ontouchup";
        public static final String ROTATION_CENTER_ID = "rotationcenterid";
        public static final String SPRINGS_TOP_THRESHOLD = "springstopthreshold";
        public static final String SPRING_BOUNDARY = "springboundary";
        public static final String SPRING_DAMPING = "springdamping";
        public static final String SPRING_MASS = "springmass";
        public static final String SPRING_STIFFNESS = "springstiffness";
        public static final String TOUCH_ANCHOR_ID = "touchanchorid";
        public static final String TOUCH_ANCHOR_SIDE = "touchanchorside";
        public static final String TOUCH_REGION_ID = "touchregionid";
        public static final String[] ON_TOUCH_UP_ENUM = {"autoComplete", "autoCompleteToStart", "autoCompleteToEnd", "stop", "decelerate", "decelerateAndComplete", "neverCompleteToStart", "neverCompleteToEnd"};
        public static final String[] SPRING_BOUNDARY_ENUM = {"overshoot", "bounceStart", "bounceEnd", "bounceBoth"};
        public static final String[] AUTOCOMPLETE_MODE_ENUM = {"continuousVelocity", "spring"};
        public static final String[] NESTED_SCROLL_FLAGS_ENUM = {"none", "disablePostScroll", "disableScroll", "supportScrollUp"};
    }

    int getId(String str);

    boolean setValue(int i, float f);

    boolean setValue(int i, int i2);

    boolean setValue(int i, String str);

    boolean setValue(int i, boolean z);

    public interface Attributes {
        public static final String NAME = "KeyAttributes";
        public static final String S_ALPHA = "alpha";
        public static final String S_CURVE_FIT = "curveFit";
        public static final String S_CUSTOM = "CUSTOM";
        public static final String S_EASING = "easing";
        public static final String S_ELEVATION = "elevation";
        public static final String S_PATH_ROTATE = "pathRotate";
        public static final String S_PIVOT_X = "pivotX";
        public static final String S_PIVOT_Y = "pivotY";
        public static final String S_PROGRESS = "progress";
        public static final String S_ROTATION_X = "rotationX";
        public static final String S_ROTATION_Y = "rotationY";
        public static final String S_ROTATION_Z = "rotationZ";
        public static final String S_SCALE_X = "scaleX";
        public static final String S_SCALE_Y = "scaleY";
        public static final String S_TRANSLATION_X = "translationX";
        public static final String S_TRANSLATION_Y = "translationY";
        public static final String S_TRANSLATION_Z = "translationZ";
        public static final String S_VISIBILITY = "visibility";
        public static final int TYPE_ALPHA = 303;
        public static final int TYPE_CURVE_FIT = 301;
        public static final int TYPE_EASING = 317;
        public static final int TYPE_ELEVATION = 307;
        public static final int TYPE_PATH_ROTATE = 316;
        public static final int TYPE_PIVOT_TARGET = 318;
        public static final int TYPE_PIVOT_X = 313;
        public static final int TYPE_PIVOT_Y = 314;
        public static final int TYPE_PROGRESS = 315;
        public static final int TYPE_ROTATION_X = 308;
        public static final int TYPE_ROTATION_Y = 309;
        public static final int TYPE_ROTATION_Z = 310;
        public static final int TYPE_SCALE_X = 311;
        public static final int TYPE_SCALE_Y = 312;
        public static final int TYPE_TRANSLATION_X = 304;
        public static final int TYPE_TRANSLATION_Y = 305;
        public static final int TYPE_TRANSLATION_Z = 306;
        public static final int TYPE_VISIBILITY = 302;
        public static final String S_FRAME = "frame";
        public static final String S_TARGET = "target";
        public static final String S_PIVOT_TARGET = "pivotTarget";
        public static final String[] KEY_WORDS = {"curveFit", "visibility", "alpha", "translationX", "translationY", "translationZ", "elevation", "rotationX", "rotationY", "rotationZ", "scaleX", "scaleY", "pivotX", "pivotY", "progress", "pathRotate", "easing", "CUSTOM", S_FRAME, S_TARGET, S_PIVOT_TARGET};

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$Attributes$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = Attributes.NAME;
            }

            public static int getType(int i) {
                if (i == 100) {
                    return 2;
                }
                if (i == 101) {
                    return 8;
                }
                switch (i) {
                    case 301:
                    case 302:
                        return 2;
                    case 303:
                    case 304:
                    case 305:
                    case 306:
                    case 307:
                    case 308:
                    case 309:
                    case 310:
                    case 311:
                    case 312:
                    case 313:
                    case 314:
                    case 315:
                    case Attributes.TYPE_PATH_ROTATE /* 316 */:
                        return 4;
                    case Attributes.TYPE_EASING /* 317 */:
                    case Attributes.TYPE_PIVOT_TARGET /* 318 */:
                        return 8;
                    default:
                        return -1;
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public static int getId(String str) {
                char c;
                str.hashCode();
                switch (str.hashCode()) {
                    case -1310311125:
                        if (str.equals("easing")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1249320806:
                        if (str.equals("rotationX")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1249320805:
                        if (str.equals("rotationY")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1249320804:
                        if (str.equals("rotationZ")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497657:
                        if (str.equals("translationX")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497656:
                        if (str.equals("translationY")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497655:
                        if (str.equals("translationZ")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1001078227:
                        if (str.equals("progress")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case -987906986:
                        if (str.equals("pivotX")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case -987906985:
                        if (str.equals("pivotY")) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case -908189618:
                        if (str.equals("scaleX")) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case -908189617:
                        if (str.equals("scaleY")) {
                            c = 11;
                            break;
                        }
                        c = 65535;
                        break;
                    case -880905839:
                        if (str.equals(Attributes.S_TARGET)) {
                            c = '\f';
                            break;
                        }
                        c = 65535;
                        break;
                    case -4379043:
                        if (str.equals("elevation")) {
                            c = '\r';
                            break;
                        }
                        c = 65535;
                        break;
                    case 92909918:
                        if (str.equals("alpha")) {
                            c = 14;
                            break;
                        }
                        c = 65535;
                        break;
                    case 97692013:
                        if (str.equals(Attributes.S_FRAME)) {
                            c = 15;
                            break;
                        }
                        c = 65535;
                        break;
                    case 579057826:
                        if (str.equals("curveFit")) {
                            c = 16;
                            break;
                        }
                        c = 65535;
                        break;
                    case 803192288:
                        if (str.equals("pathRotate")) {
                            c = 17;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1167159411:
                        if (str.equals(Attributes.S_PIVOT_TARGET)) {
                            c = 18;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1941332754:
                        if (str.equals("visibility")) {
                            c = 19;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        return Attributes.TYPE_EASING;
                    case 1:
                        return 308;
                    case 2:
                        return 309;
                    case 3:
                        return 310;
                    case 4:
                        return 304;
                    case 5:
                        return 305;
                    case 6:
                        return 306;
                    case 7:
                        return 315;
                    case '\b':
                        return 313;
                    case '\t':
                        return 314;
                    case '\n':
                        return 311;
                    case 11:
                        return 312;
                    case '\f':
                        return 101;
                    case '\r':
                        return 307;
                    case 14:
                        return 303;
                    case 15:
                        return 100;
                    case 16:
                        return 301;
                    case 17:
                        return Attributes.TYPE_PATH_ROTATE;
                    case 18:
                        return Attributes.TYPE_PIVOT_TARGET;
                    case 19:
                        return 302;
                    default:
                        return -1;
                }
            }
        }
    }

    public interface Cycle {
        public static final String NAME = "KeyCycle";
        public static final String S_ALPHA = "alpha";
        public static final String S_CURVE_FIT = "curveFit";
        public static final String S_EASING = "easing";
        public static final String S_ELEVATION = "elevation";
        public static final String S_PATH_ROTATE = "pathRotate";
        public static final String S_PIVOT_X = "pivotX";
        public static final String S_PIVOT_Y = "pivotY";
        public static final String S_PROGRESS = "progress";
        public static final String S_ROTATION_X = "rotationX";
        public static final String S_ROTATION_Y = "rotationY";
        public static final String S_ROTATION_Z = "rotationZ";
        public static final String S_SCALE_X = "scaleX";
        public static final String S_SCALE_Y = "scaleY";
        public static final String S_TRANSLATION_X = "translationX";
        public static final String S_TRANSLATION_Y = "translationY";
        public static final String S_TRANSLATION_Z = "translationZ";
        public static final String S_VISIBILITY = "visibility";
        public static final String S_WAVE_SHAPE = "waveShape";
        public static final int TYPE_ALPHA = 403;
        public static final int TYPE_CURVE_FIT = 401;
        public static final int TYPE_CUSTOM_WAVE_SHAPE = 422;
        public static final int TYPE_EASING = 420;
        public static final int TYPE_ELEVATION = 307;
        public static final int TYPE_PATH_ROTATE = 416;
        public static final int TYPE_PIVOT_X = 313;
        public static final int TYPE_PIVOT_Y = 314;
        public static final int TYPE_PROGRESS = 315;
        public static final int TYPE_ROTATION_X = 308;
        public static final int TYPE_ROTATION_Y = 309;
        public static final int TYPE_ROTATION_Z = 310;
        public static final int TYPE_SCALE_X = 311;
        public static final int TYPE_SCALE_Y = 312;
        public static final int TYPE_TRANSLATION_X = 304;
        public static final int TYPE_TRANSLATION_Y = 305;
        public static final int TYPE_TRANSLATION_Z = 306;
        public static final int TYPE_VISIBILITY = 402;
        public static final int TYPE_WAVE_OFFSET = 424;
        public static final int TYPE_WAVE_PERIOD = 423;
        public static final int TYPE_WAVE_PHASE = 425;
        public static final int TYPE_WAVE_SHAPE = 421;
        public static final String S_CUSTOM_WAVE_SHAPE = "customWave";
        public static final String S_WAVE_PERIOD = "period";
        public static final String S_WAVE_OFFSET = "offset";
        public static final String S_WAVE_PHASE = "phase";
        public static final String[] KEY_WORDS = {"curveFit", "visibility", "alpha", "translationX", "translationY", "translationZ", "elevation", "rotationX", "rotationY", "rotationZ", "scaleX", "scaleY", "pivotX", "pivotY", "progress", "pathRotate", "easing", "waveShape", S_CUSTOM_WAVE_SHAPE, S_WAVE_PERIOD, S_WAVE_OFFSET, S_WAVE_PHASE};

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$Cycle$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = Cycle.NAME;
            }

            public static int getType(int i) {
                if (i == 100) {
                    return 2;
                }
                if (i == 101) {
                    return 8;
                }
                if (i == 416) {
                    return 4;
                }
                if (i == 420 || i == 421) {
                    return 8;
                }
                switch (i) {
                    case 304:
                    case 305:
                    case 306:
                    case 307:
                    case 308:
                    case 309:
                    case 310:
                    case 311:
                    case 312:
                    case 313:
                    case 314:
                    case 315:
                        return 4;
                    default:
                        switch (i) {
                            case 401:
                            case 402:
                                return 2;
                            case 403:
                                return 4;
                            default:
                                switch (i) {
                                    case 423:
                                    case 424:
                                    case Cycle.TYPE_WAVE_PHASE /* 425 */:
                                        return 4;
                                    default:
                                        return -1;
                                }
                        }
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public static int getId(String str) {
                char c;
                str.hashCode();
                switch (str.hashCode()) {
                    case -1310311125:
                        if (str.equals("easing")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1249320806:
                        if (str.equals("rotationX")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1249320805:
                        if (str.equals("rotationY")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1249320804:
                        if (str.equals("rotationZ")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497657:
                        if (str.equals("translationX")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497656:
                        if (str.equals("translationY")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1225497655:
                        if (str.equals("translationZ")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1001078227:
                        if (str.equals("progress")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case -987906986:
                        if (str.equals("pivotX")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case -987906985:
                        if (str.equals("pivotY")) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case -908189618:
                        if (str.equals("scaleX")) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case -908189617:
                        if (str.equals("scaleY")) {
                            c = 11;
                            break;
                        }
                        c = 65535;
                        break;
                    case 92909918:
                        if (str.equals("alpha")) {
                            c = '\f';
                            break;
                        }
                        c = 65535;
                        break;
                    case 579057826:
                        if (str.equals("curveFit")) {
                            c = '\r';
                            break;
                        }
                        c = 65535;
                        break;
                    case 803192288:
                        if (str.equals("pathRotate")) {
                            c = 14;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1941332754:
                        if (str.equals("visibility")) {
                            c = 15;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        return Cycle.TYPE_EASING;
                    case 1:
                        return 308;
                    case 2:
                        return 309;
                    case 3:
                        return 310;
                    case 4:
                        return 304;
                    case 5:
                        return 305;
                    case 6:
                        return 306;
                    case 7:
                        return 315;
                    case '\b':
                        return 313;
                    case '\t':
                        return 314;
                    case '\n':
                        return 311;
                    case 11:
                        return 312;
                    case '\f':
                        return 403;
                    case '\r':
                        return 401;
                    case 14:
                        return 416;
                    case 15:
                        return 402;
                    default:
                        return -1;
                }
            }
        }
    }

    public interface Trigger {
        public static final String CROSS = "CROSS";
        public static final String[] KEY_WORDS = {"viewTransitionOnCross", "viewTransitionOnPositiveCross", "viewTransitionOnNegativeCross", "postLayout", "triggerSlack", "triggerCollisionView", "triggerCollisionId", "triggerID", "positiveCross", "negativeCross", "triggerReceiver", "CROSS"};
        public static final String NAME = "KeyTrigger";
        public static final String NEGATIVE_CROSS = "negativeCross";
        public static final String POSITIVE_CROSS = "positiveCross";
        public static final String POST_LAYOUT = "postLayout";
        public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
        public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
        public static final String TRIGGER_ID = "triggerID";
        public static final String TRIGGER_RECEIVER = "triggerReceiver";
        public static final String TRIGGER_SLACK = "triggerSlack";
        public static final int TYPE_CROSS = 312;
        public static final int TYPE_NEGATIVE_CROSS = 310;
        public static final int TYPE_POSITIVE_CROSS = 309;
        public static final int TYPE_POST_LAYOUT = 304;
        public static final int TYPE_TRIGGER_COLLISION_ID = 307;
        public static final int TYPE_TRIGGER_COLLISION_VIEW = 306;
        public static final int TYPE_TRIGGER_ID = 308;
        public static final int TYPE_TRIGGER_RECEIVER = 311;
        public static final int TYPE_TRIGGER_SLACK = 305;
        public static final int TYPE_VIEW_TRANSITION_ON_CROSS = 301;
        public static final int TYPE_VIEW_TRANSITION_ON_NEGATIVE_CROSS = 303;
        public static final int TYPE_VIEW_TRANSITION_ON_POSITIVE_CROSS = 302;
        public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
        public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
        public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$Trigger$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = Trigger.NAME;
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public static int getId(String str) {
                char c;
                str.hashCode();
                switch (str.hashCode()) {
                    case -1594793529:
                        if (str.equals("positiveCross")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -966421266:
                        if (str.equals("viewTransitionOnPositiveCross")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -786670827:
                        if (str.equals("triggerCollisionId")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -648752941:
                        if (str.equals("triggerID")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -638126837:
                        if (str.equals("negativeCross")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -76025313:
                        if (str.equals("triggerCollisionView")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -9754574:
                        if (str.equals("viewTransitionOnNegativeCross")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 64397344:
                        if (str.equals("CROSS")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case 364489912:
                        if (str.equals("triggerSlack")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1301930599:
                        if (str.equals("viewTransitionOnCross")) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1401391082:
                        if (str.equals("postLayout")) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1535404999:
                        if (str.equals("triggerReceiver")) {
                            c = 11;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        return 309;
                    case 1:
                        return 302;
                    case 2:
                        return 307;
                    case 3:
                        return 308;
                    case 4:
                        return 310;
                    case 5:
                        return 306;
                    case 6:
                        return 303;
                    case 7:
                        return 312;
                    case '\b':
                        return 305;
                    case '\t':
                        return 301;
                    case '\n':
                        return 304;
                    case 11:
                        return 311;
                    default:
                        return -1;
                }
            }
        }
    }

    public interface Position {
        public static final String[] KEY_WORDS = {"transitionEasing", "drawPath", "percentWidth", "percentHeight", "sizePercent", "percentX", "percentY"};
        public static final String NAME = "KeyPosition";
        public static final String S_DRAWPATH = "drawPath";
        public static final String S_PERCENT_HEIGHT = "percentHeight";
        public static final String S_PERCENT_WIDTH = "percentWidth";
        public static final String S_PERCENT_X = "percentX";
        public static final String S_PERCENT_Y = "percentY";
        public static final String S_SIZE_PERCENT = "sizePercent";
        public static final String S_TRANSITION_EASING = "transitionEasing";
        public static final int TYPE_CURVE_FIT = 508;
        public static final int TYPE_DRAWPATH = 502;
        public static final int TYPE_PATH_MOTION_ARC = 509;
        public static final int TYPE_PERCENT_HEIGHT = 504;
        public static final int TYPE_PERCENT_WIDTH = 503;
        public static final int TYPE_PERCENT_X = 506;
        public static final int TYPE_PERCENT_Y = 507;
        public static final int TYPE_POSITION_TYPE = 510;
        public static final int TYPE_SIZE_PERCENT = 505;
        public static final int TYPE_TRANSITION_EASING = 501;

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$Position$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = Position.NAME;
            }

            public static int getType(int i) {
                if (i == 100) {
                    return 2;
                }
                if (i == 101) {
                    return 8;
                }
                switch (i) {
                    case 501:
                    case 502:
                        return 8;
                    case 503:
                    case 504:
                    case 505:
                    case Position.TYPE_PERCENT_X /* 506 */:
                    case 507:
                        return 4;
                    case Position.TYPE_CURVE_FIT /* 508 */:
                        return 2;
                    default:
                        return -1;
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public static int getId(String str) {
                char c;
                str.hashCode();
                switch (str.hashCode()) {
                    case -1812823328:
                        if (str.equals("transitionEasing")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1127236479:
                        if (str.equals("percentWidth")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1017587252:
                        if (str.equals("percentHeight")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -827014263:
                        if (str.equals("drawPath")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -200259324:
                        if (str.equals("sizePercent")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 428090547:
                        if (str.equals("percentX")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 428090548:
                        if (str.equals("percentY")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        return 501;
                    case 1:
                        return 503;
                    case 2:
                        return 504;
                    case 3:
                        return 502;
                    case 4:
                        return 505;
                    case 5:
                        return Position.TYPE_PERCENT_X;
                    case 6:
                        return 507;
                    default:
                        return -1;
                }
            }
        }
    }

    public interface Motion {
        public static final String NAME = "Motion";
        public static final int TYPE_ANIMATE_CIRCLEANGLE_TO = 606;
        public static final int TYPE_ANIMATE_RELATIVE_TO = 605;
        public static final int TYPE_DRAW_PATH = 608;
        public static final int TYPE_EASING = 603;
        public static final int TYPE_PATHMOTION_ARC = 607;
        public static final int TYPE_PATH_ROTATE = 601;
        public static final int TYPE_POLAR_RELATIVETO = 609;
        public static final int TYPE_QUANTIZE_INTERPOLATOR = 604;
        public static final int TYPE_QUANTIZE_INTERPOLATOR_ID = 612;
        public static final int TYPE_QUANTIZE_INTERPOLATOR_TYPE = 611;
        public static final int TYPE_QUANTIZE_MOTIONSTEPS = 610;
        public static final int TYPE_QUANTIZE_MOTION_PHASE = 602;
        public static final int TYPE_STAGGER = 600;
        public static final String S_STAGGER = "Stagger";
        public static final String S_PATH_ROTATE = "PathRotate";
        public static final String S_QUANTIZE_MOTION_PHASE = "QuantizeMotionPhase";
        public static final String S_EASING = "TransitionEasing";
        public static final String S_QUANTIZE_INTERPOLATOR = "QuantizeInterpolator";
        public static final String S_ANIMATE_RELATIVE_TO = "AnimateRelativeTo";
        public static final String S_ANIMATE_CIRCLEANGLE_TO = "AnimateCircleAngleTo";
        public static final String S_PATHMOTION_ARC = "PathMotionArc";
        public static final String S_DRAW_PATH = "DrawPath";
        public static final String S_POLAR_RELATIVETO = "PolarRelativeTo";
        public static final String S_QUANTIZE_MOTIONSTEPS = "QuantizeMotionSteps";
        public static final String S_QUANTIZE_INTERPOLATOR_TYPE = "QuantizeInterpolatorType";
        public static final String S_QUANTIZE_INTERPOLATOR_ID = "QuantizeInterpolatorID";
        public static final String[] KEY_WORDS = {S_STAGGER, S_PATH_ROTATE, S_QUANTIZE_MOTION_PHASE, S_EASING, S_QUANTIZE_INTERPOLATOR, S_ANIMATE_RELATIVE_TO, S_ANIMATE_CIRCLEANGLE_TO, S_PATHMOTION_ARC, S_DRAW_PATH, S_POLAR_RELATIVETO, S_QUANTIZE_MOTIONSTEPS, S_QUANTIZE_INTERPOLATOR_TYPE, S_QUANTIZE_INTERPOLATOR_ID};

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$Motion$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = Motion.NAME;
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public static int getId(String str) {
                char c;
                str.hashCode();
                switch (str.hashCode()) {
                    case -2033446275:
                        if (str.equals(Motion.S_ANIMATE_CIRCLEANGLE_TO)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1532277420:
                        if (str.equals(Motion.S_QUANTIZE_MOTION_PHASE)) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1529145600:
                        if (str.equals(Motion.S_QUANTIZE_MOTIONSTEPS)) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1498310144:
                        if (str.equals(Motion.S_PATH_ROTATE)) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1030753096:
                        if (str.equals(Motion.S_QUANTIZE_INTERPOLATOR)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -762370135:
                        if (str.equals(Motion.S_DRAW_PATH)) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -232872051:
                        if (str.equals(Motion.S_STAGGER)) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1138491429:
                        if (str.equals(Motion.S_POLAR_RELATIVETO)) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1539234834:
                        if (str.equals(Motion.S_QUANTIZE_INTERPOLATOR_TYPE)) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1583722451:
                        if (str.equals(Motion.S_QUANTIZE_INTERPOLATOR_ID)) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1639368448:
                        if (str.equals(Motion.S_EASING)) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1900899336:
                        if (str.equals(Motion.S_ANIMATE_RELATIVE_TO)) {
                            c = 11;
                            break;
                        }
                        c = 65535;
                        break;
                    case 2109694967:
                        if (str.equals(Motion.S_PATHMOTION_ARC)) {
                            c = '\f';
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        return Motion.TYPE_ANIMATE_CIRCLEANGLE_TO;
                    case 1:
                        return Motion.TYPE_QUANTIZE_MOTION_PHASE;
                    case 2:
                        return Motion.TYPE_QUANTIZE_MOTIONSTEPS;
                    case 3:
                        return 601;
                    case 4:
                        return Motion.TYPE_QUANTIZE_INTERPOLATOR;
                    case 5:
                        return Motion.TYPE_DRAW_PATH;
                    case 6:
                        return 600;
                    case 7:
                        return Motion.TYPE_POLAR_RELATIVETO;
                    case '\b':
                        return Motion.TYPE_QUANTIZE_INTERPOLATOR_TYPE;
                    case '\t':
                        return Motion.TYPE_QUANTIZE_INTERPOLATOR_ID;
                    case '\n':
                        return Motion.TYPE_EASING;
                    case 11:
                        return Motion.TYPE_ANIMATE_RELATIVE_TO;
                    case '\f':
                        return Motion.TYPE_PATHMOTION_ARC;
                    default:
                        return -1;
                }
            }
        }
    }

    public interface Custom {
        public static final String NAME = "Custom";
        public static final String S_INT = "integer";
        public static final int TYPE_BOOLEAN = 904;
        public static final int TYPE_COLOR = 902;
        public static final int TYPE_DIMENSION = 905;
        public static final int TYPE_FLOAT = 901;
        public static final int TYPE_INT = 900;
        public static final int TYPE_REFERENCE = 906;
        public static final int TYPE_STRING = 903;
        public static final String S_FLOAT = "float";
        public static final String S_COLOR = "color";
        public static final String S_STRING = "string";
        public static final String S_BOOLEAN = "boolean";
        public static final String S_DIMENSION = "dimension";
        public static final String S_REFERENCE = "refrence";
        public static final String[] KEY_WORDS = {S_FLOAT, S_COLOR, S_STRING, S_BOOLEAN, S_DIMENSION, S_REFERENCE};

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$Custom$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = Custom.NAME;
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public static int getId(String str) {
                char c;
                str.hashCode();
                switch (str.hashCode()) {
                    case -1095013018:
                        if (str.equals(Custom.S_DIMENSION)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -891985903:
                        if (str.equals(Custom.S_STRING)) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -710953590:
                        if (str.equals(Custom.S_REFERENCE)) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 64711720:
                        if (str.equals(Custom.S_BOOLEAN)) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 94842723:
                        if (str.equals(Custom.S_COLOR)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 97526364:
                        if (str.equals(Custom.S_FLOAT)) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1958052158:
                        if (str.equals(Custom.S_INT)) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        return Custom.TYPE_DIMENSION;
                    case 1:
                        return Custom.TYPE_STRING;
                    case 2:
                        return Custom.TYPE_REFERENCE;
                    case 3:
                        return Custom.TYPE_BOOLEAN;
                    case 4:
                        return Custom.TYPE_COLOR;
                    case 5:
                        return Custom.TYPE_FLOAT;
                    case 6:
                        return Custom.TYPE_INT;
                    default:
                        return -1;
                }
            }
        }
    }

    public interface MotionScene {
        public static final String NAME = "MotionScene";
        public static final int TYPE_DEFAULT_DURATION = 600;
        public static final int TYPE_LAYOUT_DURING_TRANSITION = 601;
        public static final String S_DEFAULT_DURATION = "defaultDuration";
        public static final String S_LAYOUT_DURING_TRANSITION = "layoutDuringTransition";
        public static final String[] KEY_WORDS = {S_DEFAULT_DURATION, S_LAYOUT_DURING_TRANSITION};

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$MotionScene$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = MotionScene.NAME;
            }

            public static int getType(int i) {
                if (i != 600) {
                    return i != 601 ? -1 : 1;
                }
                return 2;
            }

            public static int getId(String str) {
                str.hashCode();
                if (str.equals(MotionScene.S_DEFAULT_DURATION)) {
                    return 600;
                }
                return !str.equals(MotionScene.S_LAYOUT_DURING_TRANSITION) ? -1 : 601;
            }
        }
    }

    public interface Transition {
        public static final String NAME = "Transitions";
        public static final String S_FROM = "from";
        public static final int TYPE_AUTO_TRANSITION = 704;
        public static final int TYPE_DURATION = 700;
        public static final int TYPE_FROM = 701;
        public static final int TYPE_INTERPOLATOR = 705;
        public static final int TYPE_PATH_MOTION_ARC = 509;
        public static final int TYPE_STAGGERED = 706;
        public static final int TYPE_TO = 702;
        public static final int TYPE_TRANSITION_FLAGS = 707;
        public static final String S_DURATION = "duration";
        public static final String S_TO = "to";
        public static final String S_PATH_MOTION_ARC = "pathMotionArc";
        public static final String S_AUTO_TRANSITION = "autoTransition";
        public static final String S_INTERPOLATOR = "motionInterpolator";
        public static final String S_STAGGERED = "staggered";
        public static final String S_TRANSITION_FLAGS = "transitionFlags";
        public static final String[] KEY_WORDS = {S_DURATION, "from", S_TO, S_PATH_MOTION_ARC, S_AUTO_TRANSITION, S_INTERPOLATOR, S_STAGGERED, "from", S_TRANSITION_FLAGS};

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$Transition$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = Transition.NAME;
            }

            public static int getType(int i) {
                if (i == 509) {
                    return 2;
                }
                switch (i) {
                    case Transition.TYPE_DURATION /* 700 */:
                        return 2;
                    case Transition.TYPE_FROM /* 701 */:
                    case Transition.TYPE_TO /* 702 */:
                        return 8;
                    default:
                        switch (i) {
                            case Transition.TYPE_INTERPOLATOR /* 705 */:
                            case Transition.TYPE_TRANSITION_FLAGS /* 707 */:
                                return 8;
                            case Transition.TYPE_STAGGERED /* 706 */:
                                return 4;
                            default:
                                return -1;
                        }
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            public static int getId(String str) {
                char c;
                str.hashCode();
                switch (str.hashCode()) {
                    case -1996906958:
                        if (str.equals(Transition.S_TRANSITION_FLAGS)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1992012396:
                        if (str.equals(Transition.S_DURATION)) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1357874275:
                        if (str.equals(Transition.S_INTERPOLATOR)) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1298065308:
                        if (str.equals(Transition.S_AUTO_TRANSITION)) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 3707:
                        if (str.equals(Transition.S_TO)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 3151786:
                        if (str.equals("from")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1310733335:
                        if (str.equals(Transition.S_PATH_MOTION_ARC)) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1839260940:
                        if (str.equals(Transition.S_STAGGERED)) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        return Transition.TYPE_TRANSITION_FLAGS;
                    case 1:
                        return Transition.TYPE_DURATION;
                    case 2:
                        return Transition.TYPE_INTERPOLATOR;
                    case 3:
                        return Transition.TYPE_AUTO_TRANSITION;
                    case 4:
                        return Transition.TYPE_TO;
                    case 5:
                        return Transition.TYPE_FROM;
                    case 6:
                        return 509;
                    case 7:
                        return Transition.TYPE_STAGGERED;
                    default:
                        return -1;
                }
            }
        }
    }
}
