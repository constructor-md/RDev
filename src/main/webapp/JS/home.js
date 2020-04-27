
var vm = new Vue({
    el: '#app',
    data: {

        userManage: false,
        permissionManage: false,
        examManage: false,
        deviceManage: false,
        temManage: false,
        softManage: false,
        faultManage: false,
        deadlineManage: false,
        userAddInfoShow: false,
        permissionAddInfoShow: false,
        deviceAddInfoShow:false,
        softAddInfoShow:false,
        faultAddInfoShow:false,
        softDialogTableVisible: false,
        temDialogTableVisible: false,
        faultDialogTableVisible: false,
        faultInfoNotNull: true,
        softInfoNotNull: true,


        userInfo: {
            name: '',
            username: '',
            role: '',
        },
        userSearchInfo: {
            jobId: null,
            name: null,
            username: null,
        },

        userAddInfo: {
            jobId: null,
            name: null,
            phoneNum: null,
            permission: null,

        },
        // null
        userData: [
            {
                jobId: '233',
                name: '暖树',
                username: 'admin',
                visible: false,
                edit: false,
                loginTime: '2020-04-22 17:06:05'
            },
            {
                jobId: '233',
                name: '灵均',
                username: 'normal',
                visible: false,
                edit: false,
                loginTime: '2020-04-22'

            }
        ],



        deviceSearchInfo: {
            id:null,
            devId: null,
            devName: null,
            devFac: null,
            location: null
        },

        deviceData: [
            {
                devId: 'csdn',
                devName: '继电器',
                RDevType:'线路保护',
                proName:'线路',
                devFac:'南瑞继保',
                softId: 0,
                temId: null,
                faultId: 0,
                edit:false,
                visible:false,
            },
            {
                devId: 'csdn',
                devName: '继电器',
                RDevType:'线路保护',
                proName:'线路',
                devFac:'南瑞继保',
                softId: 1,
                temId: null,
                faultId: 1,
                edit:false,
                visible:false,
            }
        ],
        deviceAddInfo:{
            devId:null,
            devName:null,
            rDevType:null,
            proName:null,
            switchId:null,
            devFac:null,
            location:null,
            deadline:null,
        },


        deadlineDeviceData: [
            {

            }
        ],

        permissionData: [
            {
                permission: '管理员',
                userGet: true,
                userPost: false,
                userAdd: true,
                userDelete: false,
                deviceGet: true,
                devicePost: false,
                deviceAdd: true,
                deviceDelete: false,
                temGet: true,
                temPost: false,
                temAdd: true,
                temDelete: false,
                softGet: true,
                softPost: false,
                softAdd: true,
                softDelete: false,
                faultGet: true,
                faultPost: false,
                faultAdd: true,
                faultDelete: false,
                examine: true,
                visible: false,
                edit: false
            },
        ],

        permissionOptions: [
            {
                label: '有',
                value: true,
            },
            {
                label: '无',
                value: false
            }
        ],

        permissionAddData: {
            permission: null,
            userGet: null,
            userPost: null,
            userAdd: null,
            userDelete: null,
            deviceGet: null,
            devicePost: null,
            deviceAdd: null,
            deviceDelete: null,
            temGet: null,
            temPost: null,
            temAdd: null,
            temDelete: null,
            softGet: null,
            softPost: null,
            softAdd: null,
            softDelete: null,
            faultGet: null,
            faultPost: null,
            faultAdd: null,
            faultDelete: null,
            examine: null,
        },


        faultData: {
            faultId: null,
            faultDesc: 'sss',
            upTime: 'ddd',
            name: 'www',
            phoneNum: 'rrr',
            devId:null,
            visible:false,
            add:true,
        },
        faultAddInfo:{
            devId:null,
            faultDesc:null,
            name:null,
            phoneNum:null,
        },


        softData: {
            id: null,
            softId: null,
            softName: null,
            softFac: null,
            softVer: null,
            devId:null,
            visible:false,
            add:true,
            
        },
        softAddInfo:{
            devId:null,
            softId:null,
            softName:null,
            softFac:null,
            softVer:null,
        },

        faultListData:null,

        deadlineDeviceData:null,

        temData:[
            {

            }
        ],
        
        // deviceExamData:[
        //     {

        //     }
        // ],
        // faultExamData:[
        //     {

        //     }
        // ],
        // sotfExamData:[
        //     {

        //     }
        // ],
        // temExamData:[
        //     {

        //     }
        // ],
    },

    mounted: function () {
        this.getUserInfo();
    },

    methods: {


        doUserManage: function () {
            this.userManage = true,
                this.permissionManage = false,
                this.examManage = false,
                this.deviceManage = false,
                this.temManage = false,
                this.softManage = false,
                this.faultManage = false,
                this.deadlineManage = false
        },
        doPermissionManage: function () {
            this.userManage = false,
                this.examManage = false,
                this.deviceManage = false,
                this.temManage = false,
                this.softManage = false,
                this.faultManage = false,
                this.deadlineManage = false,
                this.permissionManage = true
        },
        doDeviceManage: function () {
            this.userManage = false,
                this.permissionManage = false,
                this.examManage = false,
                this.deviceManage = true,
                this.temManage = false,
                this.softManage = false,
                this.faultManage = false,
                this.deadlineManage = false
        },
        doExamManage: function () {
            this.userManage = false,
                this.permissionManage = false,
                this.examManage = true,
                this.deviceManage = false,
                this.temManage = false,
                this.softManage = false,
                this.faultManage = false,
                this.deadlineManage = false
        },
        doFaultManage: function () {
            this.userManage = false,
                this.permissionManage = false,
                this.examManage = false,
                this.deviceManage = false,
                this.temManage = false,
                this.softManage = false,
                this.faultManage = true,
                this.deadlineManage = false
        },
        doDeadlineManage: function () {
            this.userManage = false,
                this.permissionManage = false,
                this.examManage = false,
                this.deviceManage = false,
                this.temManage = false,
                this.softManage = false,
                this.faultManage = false,
                this.deadlineManage = true
        },

        //用户管理模块
        getUserInfo: function () {

            let that = this;

            axios.get("http://localhost:8081/home/userInfo")
                .then(
                    function (res) {
                        console.log(res);
                        that.userInfo.name = res.data.name;
                        that.userInfo.username = res.data.username;
                        that.userInfo.role = res.data.role;

                    },
                    function (err) {
                        console.log(err)
                    }
                )

        },

        searchUser: function () {

            let that = this;

            axios.post("http://localhost:8081/user/get", {
                jobId: that.userSearchInfo.jobId,
                name: that.userSearchInfo.name,
                username: that.userSearchInfo.username
            })
                .then(
                    function (res) {
                        console.log(res);
                        that.userData = res.data;

                        that.userSearchInfo.jobId = null;
                        that.userSearchInfo.name = null;
                        that.userSearchInfo.username = null;
                    },
                    function (err) {
                        console.log(err);
                    },
                )

        },

        deleteUser: function (row, index) {

            let that = this;
            row.visible = false;
            axios.post("http://localhost:8081/user/delete", "userId=" + row.userId)
                .then(
                    function (res) {
                        if (res.data.status == "ok") {
                            //删除当前行
                            that.userData.splice(index, 1);
                            that.$message({
                                message: '删除成功',
                                type: 'success'
                            });

                        }
                        if (res.data.status == "err") {
                            that.$message.error('删除失败');
                        }
                    },
                    function (err) {
                        console.log(res);
                        that.$message.error('操作失败，服务器错误');
                    }
                )

        },


        updateUser: function (row) {
            console.log(row)

            let that = this;
            let rowIn = false;

            axios.post("http://localhost:8081/user/post", row).then(
                function (res) {

                    if (res.data.status == "ok") {
                        that.$message({
                            message: '修改成功',
                            type: 'success',
                        });
                        rowIn = false;
                    }
                    if (res.data.status == "err") {
                        that.$message.error('修改失败');
                    }

                },
                function (err) {
                    console.log(err);
                }
            )

            if (!rowIn) {
                row.edit = false;
            }


        },

        addUser: function () {

            let that = this;

            axios.post("http://localhost:8081", {
                jobId: that.userAddInfo.jobId,
                name: that.userAddInfo.name,
                permission: that.userAddInfo.permission,
                phoneNum: that.userAddInfo.phoneNum,
            })
                .then(
                    function (res) {

                        if (res.data.status == "ok") {
                            that.$message({
                                message: '新增用户信息成功',
                                type: 'success',
                            })
                            that.userAddInfoShow = false;
                        }
                        if (res.data.status == "jobIdNull") {
                            that.$message({
                                message: '工号不可为空',
                                type: 'error',
                            })
                        }
                        if (res.data.status == "nameNull") {
                            that.$message({
                                message: '用户名不可为空',
                                type: 'error',
                            })
                        }
                        if (res.data.status == "permissionNull") {
                            that.$message({
                                message: '权限角色不存在',
                                type: 'error',
                            })
                        }
                        if (res.data.status == "permissionEmpty") {
                            that.$message({
                                message: '权限角色不可为空',
                                type: 'error',
                            })
                        }
                        if (res.data.status == "phoneNumErr") {
                            that.$message({
                                message: '手机号格式错误',
                                type: 'error',
                            })
                        }
                        if (res.data.status == "err") {
                            that.$message({
                                message: '用户信息新增失败',
                                type: 'error',
                            })
                        }


                    },
                    function (err) {
                        console.log(err);
                    }
                )

        },

        addUserClose: function () {

            this.userAddInfoShow = false;
            this.userAddInfo.jobId = null;
            this.userAddInfo.name = null;
            this.userAddInfo.permission = null;
            this.userAddInfo.phoneNum = null;

        },


        //权限管理模块
        importPermissionInfo: function () {

            let that = this;

            axios.post("http://localhost:8081/permission/get")
                .then(
                    function (res) {

                        that.permissionData = res.data;

                    },
                    function (err) {
                        console.log(err);
                    }
                )

        },

        updatePermission: function (row) {
            console.log(row)

            let that = this;
            let rowIn = false;

            axios.post("http://localhost:8081/permission/post", row).then(
                function (res) {

                    if (res.data.status == "ok") {
                        that.$message({
                            message: '修改成功',
                            type: 'success',
                        });
                        rowIn = false;
                    }
                    if (res.data.status == "err") {
                        that.$message.error('修改失败');
                    }

                },
                function (err) {
                    console.log(err);
                }
            )

            if (!rowIn) {
                row.edit = false;
            }


        },

        addPermission: function () {
            let that = this;
            console.log(this.permissionAddData)
            axios.post("http://localhost:8081/permission/add", this.permissionAddData)
                .then(
                    function (res) {
                        console.log(res.data)
                        if (res.data.status == "ok") {
                            that.$message({
                                message: '新增成功',
                                type: 'success'
                            });
                            that.permissionAddInfoShow = false;
                        }
                        if (res.data.status == "null") {
                            that.$message({
                                message: '权限角色不可为空',
                                type: 'error'
                            });
                        }
                        if (res.data.status == "repeat") {
                            that.$message({
                                message: '该角色已存在',
                                type: 'error'
                            });
                        }
                        if (res.data.status == "err") {
                            that.$message({
                                message: '新增失败',
                                type: 'error'
                            });
                        }
                    },
                    function (err) {
                        console.log(err);
                    }
                )
        },

        addPermissionClose: function () {
            this.permissionAddInfoShow = false;
            // 参数置空
        },

        deletePermission: function (row, index) {

            let that = this;
            row.visible = false;

            axios.post("http://localhost:8081/permission/delete", "permission=" + row.permission)
                .then(
                    function (res) {

                        if (res.data.status == "ok") {
                            //删除当前行
                            that.permissionData.splice(index, 1);
                            that.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                        }
                        if (res.data.status == "null") {
                            that.$message({
                                message: '该角色不存在',
                                type: 'warning'
                            });
                        }
                        if (res.data.status == "err") {
                            that.$message({
                                message: '删除失败',
                                type: 'error'
                            });
                        }

                    },
                    function (err) {
                        console.log(err);
                    }
                )

        },


        //设备管理模块
        searchDevice: function () {

            let that = this;
            console.log(this.deviceSearchInfo);
            axios.post("http://localhost:8081/device/get", this.deviceSearchInfo)
                .then(

                    function (res) {
                        console.log(res.data);
                        that.deviceData = res.data;

                        that.softInfoNotNull = true;

                        that.faultInfoNotNull = true;

                        that.deviceSearchInfo.id = null;
                        that.deviceSearchInfo.devId = null;
                        that.deviceSearchInfo.devName = null;
                        that.deviceSearchInfo.devFac = null;
                        that.deviceSearchInfo.location = null;

                        //数据多行,需要获取行数据查询

                    },
                    function (err) {
                        console.log(err);
                    }
                )

        },

        updateDevice:function(row){

            let that = this;

            console.log(row);
            axios.post("http://localhost:8081/device/post",row)
            .then(
                function(res){
                    if(res.data.status == "ok"){
                        that.$message({
                            message:'修改成功',
                            type:'success'
                        })
                        row.edit = false;
                    }
                    if(res.data.status == "idExist"){
                        that.$message({
                            message:'设备ID已存在',
                            type:'error'
                        })
                    }
                    if(res.data.status == "idNull"){
                        that.$message({
                            message:'设备ID不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'修改失败',
                            type:'error'
                        })
                    }
                    if(res.data.status == "devIdNull"){

                        that.$message({
                            message:'设备编号不可为空',
                            type:'error'
                        })

                    }
                },
                function(err){
                    console.log(err);
                }
            )

        },

        addDevice:function(){

            let that = this;

            axios.post("http://localhost:8081/device/add",this.deviceAddInfo)
            .then(
                function(res){
                    if(res.data.status == "ok"){
                        that.$message({
                            message:'设备信息添加成功',
                            type:"success"
                        })
                        that.addDeviceClose();
                    }
                    if(res.data.status == "idExist"){
                        that.$message({
                            message:'设备ID已存在',
                            type:'error'
                        })
                    }
                    if(res.data.status == "idNull"){
                        that.$message({
                            message:'设备ID不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "devIdNull"){
                        that.$message({
                            message:'设备编号不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "devNameNull"){
                        that.$message({
                            message:'设备名称不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "locationNull"){
                        that.$message({
                            message:'地址描述不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "deadlineNull"){
                        that.$message({
                            message:'使用期限不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'设备信息添加失败',
                            type:'error'
                        })
                    }

                },
                function(err){
                    console.log(err);
                }
            )



        },

        addDeviceClose:function(){
            this.deviceAddInfo.devId = null;
            this.deviceAddInfo.devName = null;
            this.deviceAddInfo.RDevType = null;
            this.deviceAddInfo.proName = null;
            this.deviceAddInfo.devFac = null;
            this.deviceAddInfo.location = null;
            this.deviceAddInfo.deadline = null;
            this.deviceAddInfo.switchId = null;
            this.deviceAddInfoShow = false;
        },

        deleteDevice:function(row,index){

            let that = this;
            row.visible = false;
            console.log(row.id);
            axios.post("http://localhost:8081/device/delete", "id=" + row.id)
                .then(
                    function (res) {

                        if (res.data.status == "ok") {
                            //删除当前行
                            that.deviceData.splice(index, 1);
                            that.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                        }
                        if (res.data.status == "err") {
                            that.$message({
                                message: '删除失败',
                                type: 'error'
                            });
                        }

                    },
                    function (err) {
                        console.log(err);
                    }
                )

        },


        //软件管理模块
        getSoftById: function (row) {

            let that = this;

            this.softDialogTableVisible = true;

            if (row.softId != 0) {

                this.softData.id= null,
                this.softData.softId= null,
                this.softData.softName= null,
                this.softData.softFac= null,
                this.softData.softVer= null,
                this.softData.devId=null,
                this.softData.visible = false;
                this.softData.add = false;


                axios.post("http://localhost:8081/soft/get", "softId=" + row.softId)
                    .then(
                        function (res) {
                            console.log(res);
                            that.softData = res.data;
                            console.log(that.softData);
                            that.softData.visible = false;
                            that.softData.add = false;

                        },
                        function (err) {
                            console.log(err);
                        }
                    )
            } else {
                
                //将设备id记录在softAddInfo中保证设备新增的时候可以使用该数据
                //设备修改和删除的时候设备Id已经存在于搜索到设备返回的devId中。
                that.softAddInfo.devId = row.id;
                that.softData.edit = false;
                that.softData.visible = false;
                that.softData.add = true;
                that.$message({
                    message: '暂无数据',
                    type: 'warning',
                })
            }
            console.log(this.softData);

        },
        updateSoft:function(){

            let that = this;

            axios.post("http://localhost:8081/soft/post",this.softData)
            .then(
                function(res){
                    if(res.data.status == "ok"){
                        that.$message({
                            message:'修改软件信息成功',
                            type:'success'
                        })
                        that.softDialogTableVisible=false;
                        that.softAddInfo = null;
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'修改软件信息失败',
                            type:'error'
                        })
                    }
                    if(res.data.status == "softFacEmpty"){
                        that.$message({
                            message:'软件厂家不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "softNameNull"){
                        that.$message({
                            message:'软件名称不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "softVerNull"){
                        that.$message({
                            message:'软件版本不可为空',
                            type:'error'
                        })
                    }


                },
                function(err){
                    console.log(err);
                }
            )
        },

        addSoft:function(){

            let that = this;

            axios.post("http://localhost:8081/soft/add",this.softAddInfo)
            .then(
                function(res){
                    if(res.data.status == "ok"){
                        that.$message({
                            message:'新增软件信息成功',
                            type:'success'
                        })
                        that.softDialogTableVisible=false;
                        that.softAddInfo = null;
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'新增软件信息失败',
                            type:'error'
                        })
                    }
                    if(res.data.status == "softFacEmpty"){
                        that.$message({
                            message:'软件厂家不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "softNameNull"){
                        that.$message({
                            message:'软件名称不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "softVerNull"){
                        that.$message({
                            message:'软件版本不可为空',
                            type:'error'
                        })
                    }



                },
                function(err){
                    console.log(err);
                }
            )

        },

        deleteSoft:function(){

            let that = this;

            axios.post("http://localhost:8081/soft/delete",this.softData)
            .then(
                function(res){
                    if(res.data.status == "ok"){
                        that.$message({
                            message:'成功删除',
                            type:'success'
                        })
                        that.softDialogTableVisible = false;
                        that.softData.visible = false;
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'删除失败',
                            type:'error'
                        })
                    }
                },
                function(err){
                    console.log(err);
                }
            )


        },

        closeSoftAdd:function(){

            this.softAddInfo.softId="";
            this.softAddInfo.softName="";
            this.softAddInfo.softFac="";
            this.softAddInfo.softVer="";
            this.softAddInfoShow = false;

        },


        //故障管理模块
        getFaultById: function (row) {
            

            let that = this;
            this.faultDialogTableVisible = true;
            console.log(row.faultId);

            if (row.faultId != 0) {

                this.faultData.faultId= null,
                this.faultData.faultDesc= null,
                this.faultData.upTime= null,
                this.faultData.name= null,
                this.faultData.phoneNum= null,
                this.faultData.devId=null,
                this.faultData.visible = false;
                this.faultData.add = false;

                axios.post("http://localhost:8081/fault/get", "faultId=" + row.faultId)
                    .then(
                        function (res) {
                            console.log(res);
                            that.faultData = res.data;
                            that.faultData.visible = false;
                            that.faultData.add = false;

                        },
                        function (err) {
                            console.log(err);
                        }
                    )
            } else {
                //devId赋给增加参数，用于增加的时候使用
                that.faultAddInfo.devId = row.id;
                that.faultData.visible = false;
                that.faultData.add = true;
                that.$message({
                    message: '暂无数据',
                    type: 'warning',
                })
            }


        },

        updateFault:function(){

            let that = this;

            axios.post("http://localhost:8081/fault/post","faultDesc="+this.faultData.faultDesc+
                                                            "&faultId="+this.faultData.faultId+
                                                            "&username="+this.userInfo.username)
            .then(
                function(res){

                    if(res.data.status == "faultDescNull"){
                        that.$message({
                            message:'故障描述不可为空',
                            type:'error'
                        })
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'故障信息修改失败',
                            type:'error'
                        })
                    }
                    if(res.data.status == "ok"){
                        that.$message({
                            message:'故障信息修改成功',
                            type:'success'
                        })
                        that.closeFaultDialogTableVisible();
                        
                    }


                },
                function(err){
                    console.log(err);
                }
            )


        },

        addFault:function(){

            let that = this;

            axios.post("http://localhost:8081/fault/add",
            "faultDesc="+this.faultAddInfo.faultDesc+
            "&username="+this.userInfo.username+
            "&devId="+this.faultAddInfo.devId)
            .then(
                function(res){
                    
                    if(res.data.status == "ok"){

                        that.$message({
                            message:'故障上报成功',
                            type:'success'
                        })
                        that.closeFaultDialogTableVisible();
                    }
                    if(res.data.status == "err"){

                        that.$message({
                            message:'故障上报失败',
                            type:'error'
                        })

                    }
                    if(res.data.status == "faultDesc"){

                        that.$message({
                            message:'故障描述不可为空',
                            type:'error'
                        })

                    }

                },
                function(err){
                    console.log(err);
                }
            )


        },

        deleteFault:function(){

            let that = this;

            axios.post("http://localhost:8081/fault/delete","faultId="+this.faultData.faultId+"&devId="+this.faultData.devId)
            .then(
                function(res){

                    if(res.data.status == "ok"){
                        that.$message({
                            message:'删除故障信息成功',
                            type:'success'
                        })
                        that.closeFaultDialogTableVisible();
                        that.faultData.visible = false;
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'删除故障信息失败',
                            type:'error'
                        })
                    }

                },
                function(err){
                    console.log(err);
                }
            )


        },


        closeFaultAdd:function(){


            this.faultAddInfo.devId="";
            this.faultAddInfo.faultId="";
            this.faultAddInfo.faultDesc="";
            this.faultAddInfo.name="";
            this.faultAddInfo.phoneNum="";
            this.faultAddInfoShow = false;

        },



        //弹出框关闭

        closeSoftDialogTableVisible: function () {

            this.softDialogTableVisible = false;
            this.softAddInfoShow = false;
            this.softData.id= "";
            this.softData.softId= "";
            this.softData.softName= "";
            this.softData.softFac= "";
            this.softData.softVer= "";

            //devId:null,


        },
        closeTemDialogTableVisible: function () {

            this.temDialogTableVisible = false;


        },
        closeFaultDialogTableVisible: function () {
            
            this.faultDialogTableVisible = false;
            this.faultAddInfoShow = false;
            this.faultData.faultId= "";
            this.faultData.faultDesc= "";
            this.faultData.upTime= "";
            this.faultData.name= "";
            this.faultData.phoneNum= "";

        },


        getTemById: function (id) {

        },



        getFaultList:function(){

            let that = this;

            axios.get("http://localhost:8081/faultList/get")
            .then(
                function(res){

                    that.faultListData = res.data;
                    that.faultListData.visible = false;
                    that.faultListData.edit = false;

                },
                function(err){
                    console.log(err);
                }
            )



        },

        deleteFaultInList:function(row,index){

            let that = this;

            axios.post("http://localhost:8081/fault/delete","faultId="+row.faultId+"&devId="+row.id)
            .then(
                function(res){

                    if(res.data.status == "ok"){
                        that.$message({
                            message:'删除故障信息成功',
                            type:'success'
                        })
                        //删除当前行
                        that.faultListData.splice(index, 1);
                        that.faultData.visible = false;
                    }
                    if(res.data.status == "err"){
                        that.$message({
                            message:'删除故障信息失败',
                            type:'error'
                        })
                    }

                },
                function(err){
                    console.log(err);
                }
            )


        },

        getDeadlineDevice:function(){

            let that = this;

            axios.get("http://localhost:8081/deadlineDevice/get")
            .then(
                function(res){

                    that.deadlineDeviceData = res.data;

                },
                function(err){
                    console.log(err);
                }
            )



        },
        
        //登出

        logout: function () {

            let location = window.location;

            axios.get("http://localhost:8081/logout")
                .then(
                    function (res) {
                        if (res.data.status == "ok") {
                            location.replace("http://localhost:8081")
                        }
                    },
                    function (err) {
                        console.log(err);
                    }
                )

        }


    }
})
