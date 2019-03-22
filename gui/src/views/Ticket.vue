<template>
  <div id="nav">
    <router-link to="/dashboard">Dashboard</router-link> |
    <router-link to="/ticket">Ticket</router-link> |
    <router-link to="/followup">Follow Up</router-link> |
    <router-link to="/incident">Incident</router-link> |
    <router-link to="/downtime">Downtime</router-link> |
    <router-link to="/cr">CR</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/">Logout</router-link>
    </br>
    </br>
    </br>
    <h1>PG Ticket Tracker</h1>
    </br>
    <b-form-group>
      <b-container fluid class="w-75">
        <b-row class="my-1">
          <b-col sm="4">
            <b-form-input v-model="ticketNo" type="text" placeholder="Ticket no?" />
          </b-col>
          <b-col sm="4">
            <b-form-select v-model="type" type="text" placeholder="Type?" :options="ticket_type" />
          </b-col>
          <b-col sm="4">
            <b-form-select id="pgRelated" placeholder="Type?" :options="pg_related" />
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="12">
            <b-form-select id="notes" placeholder="Notes?" :options="notes" />
          </b-col>
        </b-row>
        <b-row class="my-1">
          <b-col sm="4">
            <b-form-input v-model="assignedTime" type="datetime" placeholder="Time assigned?" />
          </b-col>
          <b-col sm="4">
            <b-form-input v-model="responseTime" type="datetime" placeholder="Response time?" />
          </b-col>
          <b-col sm="4">
            <b-form-input v-model="fulfillmentTime" type="datetime" placeholder="Time fulfilled?" />
          </b-col>
        </b-row>
        <div class="addTicketBtn pb-2">
          <b-button class="w-25" variant="primary">Save</b-button>
          <b-button class="w-25" variant="danger">Reset</b-button>
        </div>
      </b-container>
    </b-form-group>
    <b-table striped hover :items="ticket_list" />
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      ticket_list: {},
      pg_related: [
        { value: null, text: 'PG related?', disabled: true },
        { value: 'Problem in other system', text: 'Problem in other system' },
        { value: 'Problem in PG', text: 'Problem in PG' },
        { value: 'Not related to PG', text: 'Not related to PG' }
      ],
      ticket_type: [
        { value: null, text: 'Incident or SR?', disabled: true },
        { value: 'Service Request', text: 'Service Request' },
        { value: 'Business Service Incident', text: 'Business Service Incident' },
        { value: 'Non-Business Service Incident', text: 'Non-Business Service Incident' }
      ],
      notes: [
        { value: 'Auto Billing (card rejected)', text: 'Auto Billing (card rejected)' },
        { value: 'Auto Billing (account was sent late to PG)', text: 'Auto Billing (account was sent late to PG)' },
        { value: 'Auto Billing (CC missmatch)', text: 'Auto Billing (CC missmatch)' },
        { value: 'Auto Billing (Account not in AB file)', text: 'Auto Billing (Account not in AB file)' },
        { value: 'Auto Billing (others)', text: 'Auto Billing (others)' },
        { value: 'Payment not credited (not posted to PG)', text: 'Payment not credited (not posted to PG)' },
        { value: 'Payment not credited (posted to wrong account)', text: 'Payment not credited (posted to wrong account)' },
        { value: 'Payment not credited (CBS error)', text: 'Payment not credited (CBS error)' },
        { value: 'Payment not credited (not found in CBS)', text: 'Payment not credited (not found in CBS)' },
        { value: 'Payment not credited (not displayed on Centouch)', text: 'Payment not credited (not displayed on Centouch)' },
        { value: 'Payment not credited (Payment via prepaid reload-SA)', text: 'Payment not credited (Payment via prepaid reload-SA)' },
        { value: 'Postpaid to Prepaid credit Transfer', text: 'Postpaid to Prepaid credit Transfer' },
        { value: 'Payment of an order', text: 'Payment of an order' },
        { value: 'TOB', text: 'TOB' },
        { value: 'Payment reversal failed', text: 'Payment reversal failed' },
        { value: 'Report request', text: 'Report request' },
        { value: 'Wrong CC charged', text: 'Wrong CC charged' },
        { value: 'PG slow response', text: 'PG slow response' },
        { value: 'OP : Error when validate prepaid/postpaid number', text: 'OP : Error when validate prepaid/postpaid number' },
        { value: 'Auto Billing rebate', text: 'Auto Billing rebate' },
        { value: 'P2P Activation', text: 'P2P Activation' },
        { value: 'Prepaid reload not credited', text: 'Prepaid reload not credited' },
        { value: 'OP : WAF rejected URL', text: 'OP : WAF rejected URL' },
        { value: 'Credit Adv', text: 'Credit Adv' },
        { value: 'OP (General)', text: 'OP (General)' },
        { value: 'Credit Sharing', text: 'Credit Sharing' },
        { value: 'Payment (General)', text: 'Payment (General)' },
        { value: 'PG Dashboard', text: 'PG Dashboard' },
        { value: 'Others (Non PG related)', text: 'Others (Non PG related)' },
        { value: 'Dealer Threshold (Threshold not updated)', text: 'Dealer Threshold (Threshold not updated)' },
        { value: 'Payment not credited (Recon file error)', text: 'Payment not credited (Recon file error)' },
        { value: 'Prepaid not credited (CBS error)', text: 'Prepaid not credited (CBS error)' },
        { value: 'Postpaid payment not found in PG but exist in CBS', text: 'Postpaid payment not found in PG but exist in CBS' },
      ]
    }
  },
  methods: {
    getTicket: function() {
      axios
        .get(
          'http://localhost:8080/ticket/baseAPI/getlistoftickets?from=2017-02-25%2000:00:01&to=2017-02-29%2000:00:01',
        )
        .then(response => {
          console.log(response);
          this.ticket_list = response.data;
        })
        .catch(function(error) {
          console.log(error);
        })
        .then(function() {
          // always executed
        });
    },
  },
  beforeMount() {
    this.getTicket();
  },
};
</script>

<style scoped>
#ticket_list {
  background-color: #ffffff;
  border: 1px solid #cccccc;
  padding: 20px;
  margin-top: 10px;
}

.addTicketBtn {
  text-align: right;
}
</style>
