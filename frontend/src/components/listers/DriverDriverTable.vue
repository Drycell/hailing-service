<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="values"
                :items-per-page="5"
                class="elevation-1"
        ></v-data-table>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog v-model="openDialog" width="500">
                    <template v-slot:activator="{ on, attrs }">
                        <v-fab-transition>
                            <v-btn
                                    color="primary"
                                    fab
                                    dark
                                    large
                                    style="position:absolute; bottom: 5%; right: 2%; z-index:99"
                                    @click="openDialog=true;"
                            >
                                <v-icon v-bind="attrs" v-on="on">mdi-plus</v-icon>
                            </v-btn>
                        </v-fab-transition>
                    </template>

                    <DriverDriver :offline="offline"  :isNew="true" :editMode="true" v-model="newValue" @add="append" v-if="tick"/>
                
                    <v-btn
                        class="gs-close-btn"
                        @click="closeDialog()"
                        depressed 
                        icon 
                        absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
    const axios = require('axios').default;
    import DriverDriver from './../DriverDriver.vue';

    export default {
        name: 'DriverDriverManager',
        components: {
            DriverDriver,
        },
        props: {
            offline: Boolean,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            values: [],
            headers: 
                [
                    { text: "id", value: "id" },
                    { text: "email", value: "email" },
                    { text: "driverLicenseNumber", value: "driverLicenseNumber" },
                    { text: "isApproved", value: "isApproved" },
                    { text: "isHailing", value: "isHailing" },
                    { text: "driverLocation", value: "driverLocation" },
                    { text: "operationRequestForm", value: "operationRequestForm" },
                    { text: "operationInfo", value: "operationInfo" },
                    { text: "operationRequestId", value: "operationRequestId" },
                ],
            driver : [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            if(this.offline){
                if(!this.values) this.values = [];
                return;
            }

            var temp = await axios.get(axios.fixUrl('/drivers'))
            temp.data._embedded.drivers.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])
            this.values = temp.data._embedded.drivers;

            this.newValue = {
                'email': '',
                'driverLicenseNumber': '',
                'isApproved': false,
                'isHailing': false,
                'driverLocation': '',
                'operationRequestForm': '',
                'operationInfo': '',
                'operationRequestId': 0,
            }
        },
        methods: {
            closeDialog(){
                this.openDialog = false
            },
            append(value){
                this.tick = false
                this.newValue = {}
                this.values.push(value)
                
                this.$emit('input', this.values);

                this.$nextTick(function(){
                    this.tick=true
                })
            },
        }
    }
</script>

