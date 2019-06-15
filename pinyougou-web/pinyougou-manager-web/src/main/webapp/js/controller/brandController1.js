/*定义品牌控制器层*/

app.controller('brandController',function ($scope,$controller,baseService) {
    /*指定继承baseController*/
    $controller('baseController',{$scope:$scope});
    /*添加品牌*/
    $scope.saveOrUpdate=function () {
        /*定义请求URL*/
        var url="save";//添加品牌
        if ($scope.entity.id){
            url="update";//修改品牌
        }
        /*发送post请求*/
        baseService.sendPost("/brand/"+url,$scope.entity)
            .then(function (response) {
                if(response.data){
                    /*重新加载品牌数据*/
                    $scope.reload();
                }else{
                    alert("添加失败!");
                }
            });
    };
    /*为修改按钮绑定点击事件*/
    $scope.show=function (entity) {
        /*把entity的json对象转化成一个新的json对象*/
        $scope.entity=JSON.parse(JSON.stringify(entity));
    };




    /*定义查询条件对象*/
    $scope.searchEntity={}
    ;
    /*多条件分页查询*/
    $scope.search=function (page,rows) {
        /*发送异步请求分页查询品牌数据*/
        /*  $http.get('/brand/findByPage?page='+page+'&rows='+rows)
              .then(function (response) {
                  $scope.dataList=response.data.rows;
                  /!*更新总记录数*!/
                  $scope.paginationConf.totalItems=response.data.total;
              });
        $http({
           url:"/brand/findByPage?page="+page+"&rows="+rows,
            method:'get',
            params:$scope.searchEntity})*/
        baseService.findByPage("/brand/findByPage",page,rows,$scope.searchEntity)
        .then(function (response) {
            $scope.dataList=response.data.rows;
            /*跟新总记录数*/
            $scope.paginationConf.totalItems=response.data.total;
        });
    };

    /*批量删除*/
    $scope.delete=function () {
        if ($scope.ids.length>0){
            /*发送异步请求*/
            baseService.deleteById("/brand/delete",$scope.ids).then(
                function (response) {
                    if (response.data){
                        /*重新加载品牌数据*/
                        $scope.reload();
                    }
                }
            );


        }else{
            alert("请选择要删除的品牌!");
        }
    }







})




