/**
 * Created by gaimeng on 15/3/9.
 */

IndoorMap3d = function(mapdiv){
    var _this = this;
    var _theme = null;
    var _mapDiv = mapdiv,
        _canvasWidth = _mapDiv.clientWidth,
        _canvasWidthHalf = _canvasWidth / 2,
        _canvasHeight = _mapDiv.clientHeight,
        _canvasHeightHalf = _canvasHeight / 2;

    var _scene, _controls, _projector, _rayCaster;
    var  _canvasDiv;
    var _selected;
	
	var _colorAnimated;
    var _showNames = true, _showPubPoints = true;
    var _curFloorId = 0;
    var _selectionListener = null;
    var _sceneOrtho, _cameraOrtho;//for 2d
    var _spriteMaterials = [], _pubPointSprites=null, _nameSprites = null;

    this.camera = null;
    this.renderer = null;
    this.mall = null;
    this.is3d = true;

    this.init = function(){

        // perspective scene for normal 3d rendering
        _scene = new THREE.Scene();
        _this.camera = new THREE.PerspectiveCamera(20, _canvasWidth / _canvasHeight, 0.1, 2000);

        //orthogonal scene for sprites 2d rendering
        _sceneOrtho = new THREE.Scene();
        _cameraOrtho = new THREE.OrthographicCamera(- _canvasWidthHalf, _canvasWidthHalf, _canvasHeightHalf, -_canvasHeightHalf, 1, 10);
        _cameraOrtho.position.z = 6;

        //renderer
        _this.renderer = new THREE.WebGLRenderer({ antialias: true, powerPreference: "high-performance" });
        _this.renderer.autoClear = false;

        //set up the lights
        var light = new THREE.DirectionalLight(0xffffff);
        light.position.set(-500, 500, -500);
        _scene.add(light);

        var light = new THREE.DirectionalLight(0xffffff);
        light.position.set(500, 500, 500);
        _scene.add(light);

        //canvas div
        _this.renderer.setSize(_mapDiv.clientWidth*2, _mapDiv.clientHeight*2);
        _canvasDiv = _this.renderer.domElement
        _mapDiv.appendChild(_canvasDiv);

        _mapDiv.style.overflow = "hidden";
        _canvasDiv.style.width = "100%";
        _canvasDiv.style.height = "100%";

        //controls
        _controls = new THREE.OrbitControls(_this.camera, _canvasDiv);
        _controls.listenToKeyEvents( window );

        _controls.enableDamping = true;
        _controls.dampingFactor = 0.05;

        _controls.screenSpacePanning = false;

        _controls.minDistance = 140;
        _controls.maxDistance = 1800;

        _controls.maxPolarAngle = Math.PI / 2;

		 _this.setDefaultView();
 

        this.setSelectionListener((obj) => {
            if (obj) {
                const { id } = obj;
                // TODO: open modal
                console.log(obj);
                console.log(id);
            }
        });
		
		  window.addEventListener('resize', (e) => {
            const { target } = e;
            const { innerHeight, innerWidth } = target;

            _this.camera.aspect = innerWidth / innerHeight;
            _this.camera.updateProjectionMatrix();
			 _this.renderer.setSize(innerWidth*2, innerHeight*2); 
			 
			_this._cameraOrtho.left = -window.innerWidth / camFactor;
			_this._cameraOrtho.right = window.innerWidth / camFactor;
			_this._cameraOrtho.top = window.innerHeight / camFactor;
			_this._cameraOrtho.bottom = -window.innerHeight / camFactor;
		   	_this._cameraOrtho.updateProjectionMatrix();
			updateLabels();
		    _this.renderer.clearDepth();
	
			
			
			
			
           
        });

    }

    this.setTheme = function(theme){
        if(_theme == null){
            _theme = theme
        } else if(_theme != theme) {
            _theme = theme;
            _this.parse(_this.mall.jsonData); //parse
        }
        return _this;
    }

    this.theme = function(){
        return _theme;
    }

    //load the map by the json file name
    this.load = function (fileName, callback) {
        var loader = new IndoorMapLoader(true);
        _theme = default3dTheme;
        loader.load(fileName, function(mall){
            _this.mall = mall;
            _scene.add(_this.mall.root);
            _scene.mall = mall;
            if(callback) {
                callback();
            }
            _this.renderer.setClearColor(_theme.background);
            if(_curFloorId == 0){
                _this.showAllFloors();
            }else{
                _this.showFloor(_curFloorId);
            }

        });
        return _this;
    }
	
	  this.loadFromJson = function (fileData, callback) {
        var loader = new IndoorMapLoader(true);
        _theme = default3dTheme;
        loader.loadFromJson(fileData, function(mall){
            _this.mall = mall;
            _scene.add(_this.mall.root);
            _scene.mall = mall;
            if(callback) {
                callback();
            }
            _this.renderer.setClearColor(_theme.background);
            if(_curFloorId == 0){
                _this.showAllFloors();
            }else{
                _this.showFloor(_curFloorId);
            }

        });
        return _this;
    }

    //parse the json file
    this.parse = function(json){
        if(_theme == null) {
            _theme = default3dTheme;
        }
        _this.mall = ParseModel(json, _this.is3d, _theme);
        _scene.mall = _this.mall;
        _this.showFloor(_this.mall.getDefaultFloorId());
        _this.renderer.setClearColor(_theme.background);
        _scene.add(_this.mall.root);
        _mapDiv.style.background = _theme.background;
        return _this;
    }

    //reset the camera to default configuration
    this.setDefaultView = function () {

		var camAngle = 10;
			if(_this.mall!=null){
		  camAngle = _this.mall.FrontAngle + Math.PI/2;
		 }
        
		_controls.reset();
        var camDir = [Math.cos(camAngle), Math.sin(camAngle)];
        var camLen = 500;
        var tiltAngle = 155.0 * Math.PI/180.0;
        _this.camera.position.set(camDir[1]*camLen, Math.sin(tiltAngle) * camLen, camDir[0]*camLen);//TODO: adjust the position automatically
        _this.camera.lookAt(_scene.position);

    
        _controls.viewChanged = true;
        return _this;
    }

    //set top view
    this.setTopView = function(){
        _this.camera.position.set(0, 800, 0);
        return _this;
    }

    //TODO:adjust camera to fit the building
    this.adjustCamera = function() {
        _this.setDefaultView();

    }

    this.zoomIn = function(zoomScale){
        _controls.zoomOut(zoomScale);
        redraw();
    }

    this.zoomOut = function(zoomScale){
        _controls.zoomIn(zoomScale);
        redraw();
    }

    //show floor by id
    this.showFloor = function(floorid) {
        _curFloorId = floorid;
        if(_scene.mall == null){
            return;
        }
        _scene.mall.showFloor(floorid);
        _this.adjustCamera();
        if(_showPubPoints) {
            createPubPointSprites(floorid);
        }
        if(_showNames) {
            createNameSprites(floorid);
        }
        redraw();
        return _this;
    }

    //show all floors
    this.showAllFloors = function(){
        _curFloorId = 0; //0 for showing all
        if(_this.mall == null){
            return;
        }
        _this.mall.showAllFloors();
        _this.adjustCamera();
        clearPubPointSprites();
        clearNameSprites();
        return _this;
    }

    //set if the objects are selectable
    this.setSelectable = function (selectable) {
        if(selectable){
            _projector = new THREE.Projector();
            _rayCaster = new THREE.Raycaster();
            _mapDiv.addEventListener('mousedown', onSelectObject, false);
            _mapDiv.addEventListener('touchstart', onSelectObject, false);
        }else{
            _mapDiv.removeEventListener('mousedown', onSelectObject, false);
            _mapDiv.removeEventListener('touchstart', onSelectObject, false);
        }
        return _this;
    }

    //set if the user can pan the camera
    this.setMovable = function(movable){
        _controls.enable = movable;
        return _this;
    }

    //show the labels
    this.showAreaNames = function(show) {
        _showNames = show == undefined ? true : show;
        return _this;
    }

    //show pubPoints(entries, ATM, escalator...)
    this.showPubPoints = function(show){
        _showPubPoints = show == undefined ? true: show;
        return _this;
    }

    //get the selected object
    this.getSelectedId = function(){
        return _selected.id;
    }

    //the callback function when sth is selected
    this.setSelectionListener = function(callback){
        _selectionListener = callback;
        return _this;
    }

    //select object by id
    this.selectById = function(id){
        var floor = _this.mall.getCurFloor();
        for(var i = 0; i < floor.children.length; i++){
            if(floor.children[i].id && floor.children[i].id == id) {
                if (_selected) {
                    _selected.material.color.setHex(_selected.currentHex);
                }
                select(floor.children[i]);
            }
        }
    }

    //select object(just hight light it)
    function select(obj){
        obj.currentHex = _selected.material.color.getHex();
        obj.material.color = new THREE.Color(_theme.selected);
        obj.scale = new THREE.Vector3(2,2,2);
    }

    function onSelectObject(event) {

        // find intersections
        event.preventDefault();
        var mouse = new THREE.Vector2();
        if(event.type == "touchstart"){
            mouse.x = ( event.touches[0].clientX / _canvasDiv.clientWidth ) * 2 - 1;
            mouse.y = -( event.touches[0].clientY / _canvasDiv.clientHeight ) * 2 + 1;
        }else {
            mouse.x = ( event.clientX / _canvasDiv.clientWidth ) * 2 - 1;
            mouse.y = -( event.clientY / _canvasDiv.clientHeight ) * 2 + 1;
        }
        var vector = new THREE.Vector3( mouse.x, mouse.y, 1 );
        vector.unproject( _this.camera);

        _rayCaster.set( _this.camera.position, vector.sub( _this.camera.position ).normalize() );

        var intersects = _rayCaster.intersectObjects( _this.mall.root.children[0].children );

        if ( intersects.length > 0 ) {

            if ( _selected != intersects[ 0 ].object ) {

                if ( _selected ) {
                    _selected.material.color.setHex( _selected.currentHex );
                }
                for(var i=0; i<intersects.length; i++) {
                    _selected = intersects[ i ].object;
                    if(_selected.type && _selected.type == "solidroom") {
                        select(_selected);
                        if(_selectionListener) {
                            _selectionListener(_selected); //notify the listener
                        }
                        break;
                    }else{
                        _selected = null;
                    }
                    if(_selected == null && _selectionListener != null){
                        _selectionListener(null);
                    }
                }
            }

        } else {

            if ( _selected ) {
                _selected.material.color.setHex( _selected.currentHex );
            }

            _selected = null;
            if(_selectionListener) {
                _selectionListener(null);
            }
        }
        redraw();

    }

    function redraw(){
        _controls.viewChanged = true;
    }

    function animate () {
        requestAnimationFrame(animate);
        _controls.update();

        _this.renderer.clear();
        _this.renderer.render(_scene, _this.camera);


        updateLabels();
		 _this.renderer.clearDepth();
        // _this.renderer.clearDepth();
        _this.renderer.render(_sceneOrtho, _cameraOrtho);

        _controls.viewChanged = false;
		
		
		
		
    }

    //load Sprites
    function loadSprites(){
        if(_this.mall != null && _spriteMaterials.length == 0){
            var images = _theme.pubPointImg;
            for(var key in images){
                var texture = (new THREE.TextureLoader).load(images[key], undefined, redraw);
                var material = new THREE.SpriteMaterial({map:texture});
                _spriteMaterials[key] = material;
            }
        }
        _spriteMaterials.isLoaded = true;
    }

    //labels includes pubPoints and shop names
    function updateLabels() {
        var mall = _this.mall;
        if(mall == null || _controls == null) {
            return;
        }
        var curFloor = mall.getCurFloor();
        if(curFloor == null){
            return;
        }

        var projectMatrix = null;

        if(_showNames) {
            if(_nameSprites != undefined){
                projectMatrix = new THREE.Matrix4();
                projectMatrix.multiplyMatrices(_this.camera.projectionMatrix, _this.camera.matrixWorldInverse);

                updateSprites(_nameSprites, projectMatrix);
            }

        }

        if(_showPubPoints){
            if(_pubPointSprites != undefined){
                if(!projectMatrix){
                    projectMatrix = new THREE.Matrix4();
                    projectMatrix.multiplyMatrices(_this.camera.projectionMatrix, _this.camera.matrixWorldInverse);
                }
                updateSprites(_pubPointSprites, projectMatrix);
            }
        }
        _controls.viewChanged = false;
    };

    //update sprites
    function updateSprites(spritelist, projectMatrix){
        for(var i = 0 ; i < spritelist.children.length; i++){
            var sprite = spritelist.children[i];
            var vec = new THREE.Vector3(sprite.oriX * 0.1, 0, -sprite.oriY * 0.1);
            vec.applyMatrix4(projectMatrix);

            var x = vec.x * _canvasWidthHalf;
            var y = vec.y * _canvasHeightHalf;
            sprite.position.set(x, y, 1);
			 

            //check collision with the former sprites
            var visible = true;
            var visibleMargin = 4;
            for(var j = 0; j < i; j++){
                var img = sprite.material.map.image;
                if(!img){ //if img is undefined (the img has not loaded)
                    visible = false;
                    break;
                }
 
                var imgWidthHalf1 = 128/12;
                var imgHeightHalf1 = 128/12;
                var rect1 = new Rect(sprite.position.x - imgWidthHalf1, sprite.position.y - imgHeightHalf1,
                        sprite.position.x + imgHeightHalf1, sprite.position.y + imgHeightHalf1 );


                var sprite2 = spritelist.children[j];
                var sprite2Pos = sprite2.position;
                var imgWidthHalf2 = 128/12;
                var imgHeightHalf2 = 128/12;
                var rect2 = new Rect(sprite2Pos.x - imgWidthHalf2, sprite2Pos.y - imgHeightHalf2,
                        sprite2Pos.x + imgHeightHalf2, sprite2Pos.y + imgHeightHalf2 );

                if(sprite2.visible && rect1.isCollide(rect2)){
                    visible = false;
                    break;
                }

                rect1.tl[0] -= visibleMargin;
                rect1.tl[1] -= visibleMargin;
                rect2.tl[0] -= visibleMargin;
                rect2.tl[1] -= visibleMargin;


                if(sprite.visible == false && rect1.isCollide(rect2)){
                    visible = false;
                    break;
                }
            }
             sprite.visible = visible;
        }
    }

    //creat the funcArea Name sprites of a floor
    function createNameSprites(floorId){
        if(!_nameSprites){
            _nameSprites = new THREE.Object3D();
        }else{
            clearNameSprites();
        }
        var funcAreaJson = _this.mall.getFloorJson(_this.mall.getCurFloorId()).FuncAreas;
        for(var i = 0 ; i < funcAreaJson.length; i++){
            var sprite = makeTextSprite(funcAreaJson[i].Name_en, _theme.fontStyle);
            sprite.oriX = funcAreaJson[i].Center[0];
            sprite.oriY = funcAreaJson[i].Center[1];
            _nameSprites.add(sprite);
        }
        _sceneOrtho.add(_nameSprites);
    }

    //create the pubpoint sprites in a floor by the floor id
    function createPubPointSprites(floorId){
        if(!_spriteMaterials.isLoaded){
            loadSprites();
        }

        if(!_pubPointSprites) {

            _pubPointSprites = new THREE.Object3D();
        }else{
            clearPubPointSprites();
        }

        var pubPointsJson = _this.mall.getFloorJson(_this.mall.getCurFloorId()).PubPoint;
        var imgWidth, imgHeight;
        for(var i = 0; i < pubPointsJson.length; i++){
            var spriteMat = _spriteMaterials[pubPointsJson[i].Type];
            if(spriteMat !== undefined) {
                imgWidth = 20, imgHeight = 20;
                var sprite = new THREE.Sprite(spriteMat);
                sprite.scale.set(imgWidth, imgHeight, 10);
                sprite.oriX = pubPointsJson[i].Outline[0][0][0];
                sprite.oriY = pubPointsJson[i].Outline[0][0][1]; 
                sprite.width = imgWidth;
                sprite.height = imgHeight;
                _pubPointSprites.add(sprite);
            }
        }
        _sceneOrtho.add(_pubPointSprites);
    }

    function clearNameSprites(){
        if(_nameSprites == null){
            return;
        }
        _nameSprites.remove(_nameSprites.children);
        _nameSprites.children.length = 0;
    }
    function clearPubPointSprites(){
        if(_pubPointSprites == null){
            return;
        }
        _pubPointSprites.remove(_pubPointSprites.children);
        _pubPointSprites.children.length = 0;
    }

    function makeTextSprite( message, parameters )
    {
          if ( parameters === undefined ) parameters = {};
        var fontface = parameters.hasOwnProperty("fontface") ? parameters["fontface"] : "Arial";
        var fontsize = parameters.hasOwnProperty("fontsize") ? parameters["fontsize"] : 512;
        var borderThickness = parameters.hasOwnProperty("borderThickness") ? parameters["borderThickness"] : 4;
        var borderColor = parameters.hasOwnProperty("borderColor") ?parameters["borderColor"] : { r:0, g:0, b:0, a:1.0 };
        var backgroundColor = parameters.hasOwnProperty("backgroundColor") ?parameters["backgroundColor"] : { r:255, g:255, b:255, a:1.0 };
        var textColor = parameters.hasOwnProperty("textColor") ?parameters["textColor"] : { r:128, g:0, b:0, a:1.0 };

        var canvas = document.createElement('canvas');
        var context = canvas.getContext('2d');
        context.font = "Bold " + fontsize + "px " + fontface;
        var metrics = context.measureText( message );
        var textWidth = metrics.width;
		
		var size = 128;
		 canvas.width = size;
		 canvas.height = 128;
		         
	 
		
		


        context.fillStyle   = "rgba(" + backgroundColor.r + "," + backgroundColor.g + "," + backgroundColor.b + "," + backgroundColor.a + ")";
        context.strokeStyle = "rgba(" + borderColor.r + "," + borderColor.g + "," + borderColor.b + "," + borderColor.a + ")";

        context.lineWidth = borderThickness;
		
		 context.fillStyle =  "#000000FF";
		  if(message=="114"){
			
			   context.fillStyle   = "rgba(" + _colorAnimated + "," + backgroundColor.g + "," + backgroundColor.b + "," + backgroundColor.a + ")";
			  _colorAnimated=_colorAnimated+1;
			  if(_colorAnimated>255)_colorAnimated=0;
			//  fontsize=50;
			 // context.fillStyle = "#FF0000";
		      //context.fillRect( 0, 0, size, size );
			//roundRect(context, borderThickness/2, borderThickness/2, (textWidth + borderThickness) * 1.1, fontsize * 1.4 + borderThickness, 8);
		}
	
       // roundRect(context, borderThickness/2, borderThickness/2, (textWidth + borderThickness) * 1.1, fontsize * 1.4 + borderThickness, 8);

	   context.font = "Bold " + fontsize + "px " + fontface;
       
		 context.measureText( message );
		 context.fillText( message, 10,64);


		
        var texture = new THREE.Texture(canvas) 
        texture.needsUpdate = true;

        var spriteMaterial = new THREE.SpriteMaterial( { map: texture, useScreenCoordinates: false } );
        var sprite = new THREE.Sprite( spriteMaterial );
        sprite.scale.set(1 * fontsize, 1 * fontsize, 10 * fontsize);
        return sprite;
    }

    //resize the map
    this.resize = function (width, height){
        _this.camera.aspect = width / height;
        _this.camera.updateProjectionMatrix();

        _this.renderer.setSize( width, height );
        _controls.viewChanged = true;
    }

    _this.init();
    animate();

}