package com.wyy.qgcloud.enity;

/**
 * 用户登录后的验证信息
 */
public class LoginInfo {


    /**
     * status : 登录状态
     * message : 失败的原因，成功则为null
     * data : {"userId":"用户id","email":"用户邮箱","password":"用户密码","icon":"用户头像(IO流数据)","userName":"用户姓名","phone":"手机号","role":"用户权限","groupId":"群组Id"}
     */

    private boolean status;
    private String message;
    private DataBean data;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 用户id
         * email : 用户邮箱
         * password : 用户密码
         * icon : 用户头像(IO流数据)
         * userName : 用户姓名
         * phone : 手机号
         * role : 用户权限
         * groupId : 群组Id
         */

        private int userId;
        private String email;
        private String password;
        private String icon;
        private String userName;
        private String phone;
        private int role;
        private int groupId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }
    }
}
