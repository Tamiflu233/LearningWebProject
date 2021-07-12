import Vue from 'vue';
import VueRouter from 'vue-router';
// 懒加载组件
const Login = () => import("views/login/Login")
const Register = () => import('views/login/Register')
const Admin = () => import("views/admin/Admin")
const SchoolManage = () => import("views/admin/School/SchoolManage")
const LessonManage = () => import("views/admin/Lesson/LessonManage")
const TeacherManage = () => import("views/admin/Teacher/TeacherManage")
const TeacherAccountManage = () => import("views/admin/Teacher/TeacherAccountManage")
const TeacherLessonManage = () => import('views/admin/Lesson/TeacherLessonManage')
const StudentHome = () => import('views/student/StudentHome')
const TeacherHome = () => import('views/teacher/TeacherHome')
const AllQuestions = () => import('views/student/AllQuestions/AllQuestions')
const LessonList = () => import('views/student/LessonList/LessonList')
const LessonQuestions = () => import('views/student/LessonQuestions/LessonQuestions')
const MyQuestions = () => import('views/student/MyQuestions/MyQuestions')
const QuestionDetail = () => import('views/student/QuestionDetail/QuestionDetail')
const NeededQuestions = () => import('views/teacher/NeededQuestions/NeededQuestions')
const TeaLessonQuestions = () => import('views/teacher/TeaLessonQuestions/TeaLessonQuestions')
const TeaLessons = () => import('views/teacher/TeaLessons/TeaLessons')
const TeaQuestionDetail = () => import('views/teacher/TeaQuestionDetail/TeaQuestionDetail')
// const richText = ()=> import('components/common/richText')
const TeaLessonFiles = () => import('views/teacher/TeaLessonFiles/TeaLessonFiles')
const StuLessonFiles = () => import('views/student/StuLessonFiles/StuLessonFiles')
// 安装Vue-Router插件
Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
    meta: {
      title: '登录'
    }
  },
  {
    path: '/register',
    component: Register,
    meta: {
      title: '注册'
    }
  },
  {
    path: '/admin',
    // 指定的组件
    component: Admin,
    redirect: '/admin/school',
    meta: {
      title: '后台管理系统'
    },
    children: [
      {
        path:'school',
        component: SchoolManage
      },
      {
        path: 'lesson',
        component: LessonManage
      },
      {
        path: 'teacher',
        component: TeacherManage
      },
      {
        path: 'teacherAccount',
        component: TeacherAccountManage
      },
      {
        path: 'teacherLesson',
        component: TeacherLessonManage
      }
    ]
  },
  {
    path: '/student',
    component: StudentHome,
    redirect: '/student/lessonlist',
    meta:{
      title: '学生主页'
    },
    children: [
      {
        path: 'allquestions',
        component: AllQuestions
      },
      {
        path: 'lessonlist',
        component: LessonList
      },
      {
        path: 'lessonquestions',
        component: LessonQuestions
      },
      {
        path: 'myquestions',
        component: MyQuestions
      },
      {
        path: 'questiondetail',
        component: QuestionDetail
      },
      {
        path: 'stuLessonFiles',
        component: StuLessonFiles
      }
      
    ]
  },
  {
    path: '/teacher',
    component: TeacherHome,
    redirect: '/teacher/tealessons',
    meta:{
      title: '教师主页'
    },
    children: [
      {
        path: 'neededquestions',
        component: NeededQuestions
      },
      {
        path: 'tealessonquestions',
        component: TeaLessonQuestions
      },
      {
        path: 'tealessons',
        component: TeaLessons
      },
      {
        path: 'teaquestiondetail',
        component: TeaQuestionDetail
      },
     {
       path: 'teaLessonFiles',
       component: TeaLessonFiles
     }
    ]
  },
  
  //给路由传参数: 1.动态路由->path:'/detail/:id'
    // 2. this.$router.push({path:'/detail', query: {id = }})



]
// 创建router
const router = new VueRouter({
  routes,
  scrollBehavior: () => ({ y: 0 }),
  mode: 'history',
})

//  使用前置勾子hook(参数为导航守卫函数) , 即路由跳转之前的回调,又叫前置守卫(guard)
router.beforeEach((to, from, next) => {
  // 相当于内部重写方法，其
  // 监听全局路由,如果没有token就直接返回login
  if (to.path === '/login' || to.path === '/register') {
    document.title = to.matched[0].meta.title;
    next();
  } else {
    let token = localStorage.getItem('Authorization');
    // console.log(token);
    if (token === null || token === '') {
      document.title = to.matched[0].meta.title;
      Vue.prototype.$message({
        type: 'warning',
        message: '请先登录!'
      })
      next('/login');
    } else {
      document.title = to.matched[0].meta.title;
      next();
    }
  }


})

export default router;