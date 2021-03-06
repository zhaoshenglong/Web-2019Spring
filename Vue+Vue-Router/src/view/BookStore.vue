<template>
  <div id="store-container">
    <div id="store-cover">
      <BookHeader/>
      <div id="info-bar">
        <div id="info-container">
          <span
            class="info-item"
            id="user-label"
            @mouseenter="showSignOut = true"
            @mouseleave="showSignOut = false"
            v-if="signed"
          >
            <img id="user-avatar" :src="user.avatar" alt="avatar">
            <span>{{user.name}}</span>
            <span v-show="showSignOut" id="signout-label" @click="closeSession">Sign out</span>
          </span>
          <span class="info-item" id="login" @click="toSignIn" v-if="!signed">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#iconuser"></use>
            </svg>
            sign in
          </span>
          <span class="info-item" id="register" @click="toSignUp" v-if="!signed">sign up</span>
          <span class="info-item" id="order" @click="toOrder">
            Your order
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#iconorder"></use>
            </svg>
          </span>
          <span class="info-item" id="cart" @click="toCart">
            Your cart
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#iconiconfontcart-copy"></use>
            </svg>
          </span>
          <span class="info-item" id="want">
            Favorite
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#iconfavorite"></use>
            </svg>
          </span>
          <span class="info-item" id="set" @click="toSetting">
            setting
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#iconconfig"></use>
            </svg>
          </span>
          <span class="clear"></span>
        </div>
      </div>
      <div id="main-cover">
        <div id="back-to-store-left" @click="toStore" v-if="showBack">Back to store</div>
        <div id="back-to-store-right" @click="toStore" v-if="showBack">Back to store</div>
        <router-view name="main" :user="User"></router-view>
      </div>
      <BookFooter/>
    </div>
  </div>
</template>
<script>
import BookHeader from "../components/page/Header";
import BookFooter from "../components/page/Footer";
import { mapState, mapActions } from "vuex";
import axios from "axios";
export default {
  name: "BookStore",
  data() {
    return {
      showSignOut: false,
      User: {}
    };
  },
  components: {
    BookHeader,
    BookFooter
  },
  methods: {
    toSignIn() {
      this.$router.push({ name: "SignIn" });
    },
    toSignUp() {
      this.$router.push({ name: "SignUp" });
    },
    toOrder() {
      if (this.signed) {
        this.$router.push({
          name: "Order",
          params: { userid: this.user.name }
        });
      } else {
        this.$message({
          type: "warning",
          message: "您还没有登录,请先登录～"
        });
      }
    },
    toCart() {
      if (this.signed) {
        this.$router.push({ name: "Cart", params: { userid: this.user.name } });
      } else {
        this.$message({
          type: "warning",
          message: "您还没有登录,请先登录～"
        });
      }
    },
    toSetting() {
      if (this.signed) {
        this.$router.push({
          name: "SettingProfile",
          params: { userid: this.user.name }
        });
      } else {
        this.$message({
          type: "warning",
          message: "您还没有登录,请先登录～"
        });
      }
    },
    toStore() {
      this.$router.push({ name: "StorePage" });
    },
    establishSession() {
      this.$store
        .dispatch("getStatus")
        .then(data => {
          console.log(data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    closeSession() {
      this.$store
        .dispatch("signOut")
        .then(user => {
          this.$message({
            type: "success",
            message: "您已成功退出登录!",
            duration: 1000
          });
          setTimeout(() => {
            let currentRoute = this.$route;
            if (
              currentRoute.meta.role === "user" ||
              currentRoute.meta.role === "admin"
            ) {
              this.$message({
                type: "warning",
                message: "当前页面需要您登录才能访问!",
                duration: 2000
              });
              setTimeout(() => {
                this.$router.push("signIn");
              }, 2000);
            }
          }, 1000);
        })
        .catch(err => {
          this.$message({
            type: "error",
            message: "退出登录失败，我们的服务器可能挂了:(",
            duration: 2000
          }),
            console.log(err);
        });
    }
  },
  computed: {
    ...mapState(["user", "signed"]),
    showBack() {
      if (this.$route.name == "StorePage") return false;
      return true;
    }
  },
  mounted() {
    this.establishSession();
  }
};
</script>
<style scoped>
#store-container {
  position: relative;
  z-index: 1;
  width: 100%;
  min-width: 1024px;
  min-height: 100%;
  height: 100%;
  background-image: url("../../static/background/sunshine.jpg");
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
}
#store-cover {
  width: 100%;
  height: 100%;
  background: linear-gradient(
    to bottom,
    rgba(255, 255, 255, 0.4),
    rgba(255, 255, 255, 0.05)
  );
}
#info-bar {
  width: 100%;
  height: 36px;
  margin-top: 3px;
  margin-bottom: 12px;
  background: rgba(229, 252, 251, 0.8);
}
#info-container {
  width: 1024px;
  height: 32px;
  padding: 2px 0;
  margin: 0 auto;
}
.info-item {
  float: left;
  display: block;
  border: 1px solid transparent;
  height: 32px;
  line-height: 32px;
  text-align: center;
  font-size: 14px;
  margin: 0 auto;
}
.info-item:hover {
  cursor: pointer;
  opacity: 0.7;
  box-shadow: 0 1px 6px 0 rgba(32, 33, 36, 0.5);
  border-top: 1px solid #35a3c4;
  border-bottom: 1px solid #35a3c4;
}
#user-label {
  margin-left: 50px;
  position: relative;
  z-index: 10;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
#user-avatar {
  width: 30px;
  height: 30px;
  border-radius: 100px;
  margin-right: 8px;
}
#signout-label {
  position: absolute;
  bottom: -31px;
  height: 30px;
  width: 100%;
  color: rgb(15, 15, 15);
  background: rgba(229, 252, 251, 1);
  font-weight: 500;
}
.icon {
  position: relative;
  top: 4px;
}
#login {
  margin-left: 110px;
  position: relative;
}
#login:after {
  content: "/";
  position: absolute;
  right: -12px;
  top: 0;
}
#register {
  margin-left: 24px;
}
#order {
  margin-left: 280px;
  width: 110px;
}
#cart {
  margin-left: 16px;
}
#want {
  margin-left: 16px;
}
#set {
  margin-left: 32px;
}
.clear {
  clear: both;
}
#back-to-store-left {
  position: absolute;
  left: 0;
  top: -32px;
  height: 32px;
  line-height: 32px;
  background: rgba(229, 252, 251, 1);
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  font-size: 24px;
  color: #35a3c4;
}
#back-to-store-right {
  position: absolute;
  right: 0;
  top: -32px;
  height: 32px;
  line-height: 32px;
  background: rgba(229, 252, 251, 1);
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  font-size: 24px;
  color: #35a3c4;
}
#back-to-store-left:hover,
#back-to-store-right:hover {
  text-decoration: underline;
  cursor: pointer;
}
#main-cover {
  display: flex;
  background: rgba(229, 252, 251, 0.7);
  min-width: 1004px;
  width: 60%;
  height: 100%;
  min-height: 1000px;
  margin: 122px auto 36px;
  position: relative;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
  border-left: 20px solid #35a3c4;
}
</style>
