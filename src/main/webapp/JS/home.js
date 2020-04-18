
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
            devId: null,
            devName: null,
            devFac: null,
            location: null
        },

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
        faultData: {
            faultId: null,
            faultDesc: 'sss',
            upTime: 'ddd',
            name: 'www',
            phoneNum: 'rrr',
        },
        softData: {
            id: null,
            softId: 'scc',
            softName: 'sss',
            softFac: 'ddd',
            softVer: 'ddd',
        },
        // temData:[
        //     {

        //     }
        // ],
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

        getSoftById: function (row) {

            

            let that = this;

            this.softDialogTableVisible = true;

            if (row.softId != 0) {
                this.softData = null;
                axios.post("http://localhost:8081/soft/get", "softId=" + row.softId)
                    .then(
                        function (res) {
                            console.log(res);
                            that.softData = res.data;

                        },
                        function (err) {
                            console.log(err);
                        }
                    )
            } else {
                that.$message({
                    message: '暂无数据',
                    type: 'warning',
                })
            }


        },

        getFaultById: function (row) {
            

            let that = this;
            this.faultDialogTableVisible = true;
            console.log(row.faultId);

            if (row.faultId != 0) {
                this.faultData = null;
                axios.post("http://localhost:8081/fault/get", "faultId=" + row.faultId)
                    .then(
                        function (res) {
                            console.log(res);
                            that.faultData = res.data;

                        },
                        function (err) {
                            console.log(err);
                        }
                    )
            } else {
                that.$message({
                    message: '暂无数据',
                    type: 'warning',
                })
            }


        },

        closeSoftDialogTableVisible: function () {

            this.softDialogTableVisible = false;

        },
        closeTemDialogTableVisible: function () {

            this.temDialogTableVisible = false;


        },
        closeFaultDialogTableVisible: function () {

            this.faultDialogTableVisible = false;

        },


        getTemById: function (id) {

        },

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
