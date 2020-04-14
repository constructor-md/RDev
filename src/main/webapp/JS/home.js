
var vm = new Vue({
    el: '#app',
    data: {
        user: {
            name: '暖树',
            username: 'admin',
            role: '管理员'
        },
            userManage: false,
            permissionManage: false,
            examManage: false,
            deviceManage: false,
            temManage: false,
            softManage: false,
            faultManage: false,
            deadlineManage: false,

        userData: [
            {
                jobId: '233',
                name: '暖树',
                username: 'admin',
                permission: '管理员',
                phoneNum: '13900000000',
                loginTime: '2020-4-11'
            },
            {
                jobId: '239',
                name: '张三',
                username: 'rest',
                permission: '普通用户',
                phoneNum: '13000000000',
                loginTime: '2020-4-11'
            },
            {
                jobId: '240',
                name: '李四',
                username: 'test',
                permission: '审批员',
                phoneNum: '17700000000',
                loginTime: '2020-4-11'
            }
        ],
        permissionData:[
            {
            permission:'管理员',
            userGet:'yes',
            userPost:'no',
            userAdd:'yes',
            userDelete:'no',


            }
        ],
        // deviceData:[
        //     {
        //         deviceId:'cd57x633',
        //         devName:''
        //     }
        // ],
        deadlineDeviceData:[
            {

            }
        ],
        faultData:[
            {

            }
        ],
        // softData:[
        //     {

        //     }
        // ],
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

    mounted:function(){
        this.getUserInfo;
    },

    methods:{
        
        doUserManage:function(){
            this.userManage= true,
            this.permissionManage= false,
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false
        },
        doPermissionManage:function(){
            this.userManage= false,
            this.permissionManage= true,
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false
        },
        doDeviceManage:function(){
            this.userManage= false,
            this.permissionManage= false,
            this.examManage= false,
            this.deviceManage= true,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false
        },
        doExamManage:function(){
            this.userManage= false,
            this.permissionManage= false,
            this.examManage= true,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= false
        },
        doFaultManage:function(){
            this.userManage= false,
            this.permissionManage= false,
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= true,
            this.deadlineManage= false
        },
        doDeadlineManage:function(){
            this.userManage= false,
            this.permissionManage= false,
            this.examManage= false,
            this.deviceManage= false,
            this.temManage= false,
            this.softManage= false,
            this.faultManage= false,
            this.deadlineManage= true
        },

        getUserInfo:function(){

            let that = this;

            axios.get("http://localhost:8081/home/userInfo")

        },


    }
})
