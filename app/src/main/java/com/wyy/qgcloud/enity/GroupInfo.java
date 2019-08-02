package com.wyy.qgcloud.enity;

import java.util.List;

public class GroupInfo {
    /**
     * status : true
     * message : null
     * data : {"Design":[{"userId":6,"email":"1119855062@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"王煜墉","phone":"17722106218","role":0,"groupId":6,"groupName":"设计组"}],"Graphics":[{"userId":8,"email":"10484616525@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"吴亦凡","phone":"13413452639","role":0,"groupId":7,"groupName":"图形组"}],"Ungrouped":[{"userId":11,"email":"15526345962@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/53e5ab70fe8745d5a202d14ac68d2c4b.jpg","userName":"陈宇","phone":"13526234863","role":0,"groupId":8,"groupName":"未分组"},{"userId":12,"email":"15526345968@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/e41abb019a154eba86a8c58322c17afa.png","userName":"陈宇","phone":"13526234866","role":0,"groupId":8,"groupName":"未分组"}],"Background":[{"userId":1,"email":"1048461654@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"超级管理员","phone":"13413779542","role":3,"groupId":1,"groupName":"后台组"},{"userId":7,"email":"1048461658@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"吴亦凡","phone":"13413452635","role":0,"groupId":1,"groupName":"后台组"}],"Frontend":[{"userId":2,"email":"1048461655@qq.com","password":"7e5482d6e0e3d8f8a2efee6e8ec44a4c","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"薛之谦","phone":"19924337664","role":2,"groupId":2,"groupName":"前端组"},{"userId":9,"email":"10484615@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"吴亦凡","phone":"13413452621","role":0,"groupId":2,"groupName":"前端组"}],"Embedded":[{"userId":5,"email":"12345679@qq.com","password":"3fb7cf9b0822dfb8a3c0e59e1e7bad8d","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"魏晓","phone":"13411247089","role":0,"groupId":5,"groupName":"嵌入式组"}],"Mobile":[{"userId":3,"email":"1048461657@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"蔡徐坤","phone":"13413452632","role":1,"groupId":3,"groupName":"移动组"},{"userId":10,"email":"1163401806@qq.com","password":"cf48ec06a5383231663a7f1db7a76ee5","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"魏晓","phone":"13411247075","role":0,"groupId":3,"groupName":"移动组"}],"DataMining":[{"userId":4,"email":"1119855061@qq.com","password":"25f9e794323b453885f5181f1b624d0b","icon":"http://106.15.95.10:8080/image/onepieced.jpg","userName":"王煜墉","phone":"17722106219","role":1,"groupId":4,"groupName":"数据挖掘组"}]}
     */

    private boolean status;
    private Object message;
    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<DesignBean> Design;
        private List<GraphicsBean> Graphics;
        private List<UngroupedBean> Ungrouped;
        private List<BackgroundBean> Background;
        private List<FrontendBean> Frontend;
        private List<EmbeddedBean> Embedded;
        private List<MobileBean> Mobile;
        private List<DataMiningBean> DataMining;

        public List<DesignBean> getDesign() {
            return Design;
        }

        public void setDesign(List<DesignBean> Design) {
            this.Design = Design;
        }

        public List<GraphicsBean> getGraphics() {
            return Graphics;
        }

        public void setGraphics(List<GraphicsBean> Graphics) {
            this.Graphics = Graphics;
        }

        public List<UngroupedBean> getUngrouped() {
            return Ungrouped;
        }

        public void setUngrouped(List<UngroupedBean> Ungrouped) {
            this.Ungrouped = Ungrouped;
        }

        public List<BackgroundBean> getBackground() {
            return Background;
        }

        public void setBackground(List<BackgroundBean> Background) {
            this.Background = Background;
        }

        public List<FrontendBean> getFrontend() {
            return Frontend;
        }

        public void setFrontend(List<FrontendBean> Frontend) {
            this.Frontend = Frontend;
        }

        public List<EmbeddedBean> getEmbedded() {
            return Embedded;
        }

        public void setEmbedded(List<EmbeddedBean> Embedded) {
            this.Embedded = Embedded;
        }

        public List<MobileBean> getMobile() {
            return Mobile;
        }

        public void setMobile(List<MobileBean> Mobile) {
            this.Mobile = Mobile;
        }

        public List<DataMiningBean> getDataMining() {
            return DataMining;
        }

        public void setDataMining(List<DataMiningBean> DataMining) {
            this.DataMining = DataMining;
        }

        public static class DesignBean {
            /**
             * userId : 6
             * email : 1119855062@qq.com
             * password : 25f9e794323b453885f5181f1b624d0b
             * icon : http://106.15.95.10:8080/image/onepieced.jpg
             * userName : 王煜墉
             * phone : 17722106218
             * role : 0
             * groupId : 6
             * groupName : 设计组
             */

            private int userId;
            private String email;
            private String password;
            private String icon;
            private String userName;
            private String phone;
            private int role;
            private int groupId;
            private String groupName;

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

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }

        public static class GraphicsBean {
            /**
             * userId : 8
             * email : 10484616525@qq.com
             * password : 25f9e794323b453885f5181f1b624d0b
             * icon : http://106.15.95.10:8080/image/onepieced.jpg
             * userName : 吴亦凡
             * phone : 13413452639
             * role : 0
             * groupId : 7
             * groupName : 图形组
             */

            private int userId;
            private String email;
            private String password;
            private String icon;
            private String userName;
            private String phone;
            private int role;
            private int groupId;
            private String groupName;

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

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }

        public static class UngroupedBean {
            /**
             * userId : 11
             * email : 15526345962@qq.com
             * password : 25f9e794323b453885f5181f1b624d0b
             * icon : http://106.15.95.10:8080/image/53e5ab70fe8745d5a202d14ac68d2c4b.jpg
             * userName : 陈宇
             * phone : 13526234863
             * role : 0
             * groupId : 8
             * groupName : 未分组
             */

            private int userId;
            private String email;
            private String password;
            private String icon;
            private String userName;
            private String phone;
            private int role;
            private int groupId;
            private String groupName;

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

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }

        public static class BackgroundBean {
            /**
             * userId : 1
             * email : 1048461654@qq.com
             * password : 25f9e794323b453885f5181f1b624d0b
             * icon : http://106.15.95.10:8080/image/onepieced.jpg
             * userName : 超级管理员
             * phone : 13413779542
             * role : 3
             * groupId : 1
             * groupName : 后台组
             */

            private int userId;
            private String email;
            private String password;
            private String icon;
            private String userName;
            private String phone;
            private int role;
            private int groupId;
            private String groupName;

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

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }

        public static class FrontendBean {
            /**
             * userId : 2
             * email : 1048461655@qq.com
             * password : 7e5482d6e0e3d8f8a2efee6e8ec44a4c
             * icon : http://106.15.95.10:8080/image/onepieced.jpg
             * userName : 薛之谦
             * phone : 19924337664
             * role : 2
             * groupId : 2
             * groupName : 前端组
             */

            private int userId;
            private String email;
            private String password;
            private String icon;
            private String userName;
            private String phone;
            private int role;
            private int groupId;
            private String groupName;

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

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }

        public static class EmbeddedBean {
            /**
             * userId : 5
             * email : 12345679@qq.com
             * password : 3fb7cf9b0822dfb8a3c0e59e1e7bad8d
             * icon : http://106.15.95.10:8080/image/onepieced.jpg
             * userName : 魏晓
             * phone : 13411247089
             * role : 0
             * groupId : 5
             * groupName : 嵌入式组
             */

            private int userId;
            private String email;
            private String password;
            private String icon;
            private String userName;
            private String phone;
            private int role;
            private int groupId;
            private String groupName;

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

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }

        public static class MobileBean {
            /**
             * userId : 3
             * email : 1048461657@qq.com
             * password : 25f9e794323b453885f5181f1b624d0b
             * icon : http://106.15.95.10:8080/image/onepieced.jpg
             * userName : 蔡徐坤
             * phone : 13413452632
             * role : 1
             * groupId : 3
             * groupName : 移动组
             */

            private int userId;
            private String email;
            private String password;
            private String icon;
            private String userName;
            private String phone;
            private int role;
            private int groupId;
            private String groupName;

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

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }

        public static class DataMiningBean {
            /**
             * userId : 4
             * email : 1119855061@qq.com
             * password : 25f9e794323b453885f5181f1b624d0b
             * icon : http://106.15.95.10:8080/image/onepieced.jpg
             * userName : 王煜墉
             * phone : 17722106219
             * role : 1
             * groupId : 4
             * groupName : 数据挖掘组
             */

            private int userId;
            private String email;
            private String password;
            private String icon;
            private String userName;
            private String phone;
            private int role;
            private int groupId;
            private String groupName;

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

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }
    }

//
//    private String status;
//    private String message;
//    private DataBean data;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//
//        private List<GroupListBean> groupList;
//
//        public List<GroupListBean> getGroupList() {
//            return groupList;
//        }
//
//        public void setGroupList(List<GroupListBean> groupList) {
//            this.groupList = groupList;
//        }
//
//        public static class GroupListBean {
//
//            private List<UserListBean> userList;
//
//            public List<UserListBean> getUserList() {
//                return userList;
//            }
//
//            public void setUserList(List<UserListBean> userList) {
//                this.userList = userList;
//            }
//
//            public static class UserListBean {
//                /**
//                 * user : 成员
//                 */
//                private  User user;
//
//                public User getUser() {
//                    return user;
//                }
//
//                public void setUser(User user) {
//                    this.user = user;
//                }
//
//                public static class User {
//            /**
//             * userId : 6
//             * email : 1119855062@qq.com
//             * password : 25f9e794323b453885f5181f1b624d0b
//             * icon : http://106.15.95.10:8080/image/onepieced.jpg
//             * userName : 王煜墉
//             * phone : 17722106218
//             * role : 0
//             * groupId : 6
//             * groupName : 设计组
//             */
//
//            private int userId;
//            private String email;
//            private String password;
//            private String icon;
//            private String userName;
//            private String phone;
//            private int role;
//            private int groupId;
//            private String groupName;
//
//            public int getUserId() {
//                return userId;
//            }
//
//            public void setUserId(int userId) {
//                this.userId = userId;
//            }
//
//            public String getEmail() {
//                return email;
//            }
//
//            public void setEmail(String email) {
//                this.email = email;
//            }
//
//            public String getPassword() {
//                return password;
//            }
//
//            public void setPassword(String password) {
//                this.password = password;
//            }
//
//            public String getIcon() {
//                return icon;
//            }
//
//            public void setIcon(String icon) {
//                this.icon = icon;
//            }
//
//            public String getUserName() {
//                return userName;
//            }
//
//            public void setUserName(String userName) {
//                this.userName = userName;
//            }
//
//            public String getPhone() {
//                return phone;
//            }
//
//            public void setPhone(String phone) {
//                this.phone = phone;
//            }
//
//            public int getRole() {
//                return role;
//            }
//
//            public void setRole(int role) {
//                this.role = role;
//            }
//
//            public int getGroupId() {
//                return groupId;
//            }
//
//            public void setGroupId(int groupId) {
//                this.groupId = groupId;
//            }
//
//            public String getGroupName() {
//                return groupName;
//            }
//
//            public void setGroupName(String groupName) {
//                this.groupName = groupName;
//            }
//        }
//
//            }
//        }
//    }




}
