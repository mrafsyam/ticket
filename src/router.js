import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Dashboard from './views/Dashboard.vue';
import Ticket from './views/Ticket.vue';
import FollowUp from './views/FollowUp.vue';
import Incident from './views/Incident.vue';
import Downtime from './views/Downtime.vue';
import CR from './views/Cr.vue';
import About from './views/About.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login,
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: Dashboard,
    },
    {
      path: '/ticket',
      name: 'ticket',
      component: Ticket,
    },
    {
      path: '/followup',
      name: 'followup',
      component: FollowUp,
    },
        {
      path: '/incident',
      name: 'incident',
      component: Incident,
    },
        {
      path: '/downtime',
      name: 'downtime',
      component: Downtime,
    },
            {
      path: '/cr',
      name: 'cr',
      component: CR,
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: function() {
        return import(/* webpackChunkName: "about" */ './views/About.vue');
      },
    },
  ],
});
