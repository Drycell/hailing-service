<template>
    <div>
        <v-card-title>
            OperationStatus
        </v-card-title>
        <v-card-text>
            <div v-if="editMode">
                <v-select
                        v-model="newValue"
                        :label="'name'"
                        :items="operationStatusList"
                ></v-select>
            </div>
            <div v-else>
                OperationStatus : {{ value }}
            </div>
        </v-card-text>
    </div>
</template>

<script>
    export default {
        name: 'OperationStatus',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
            operationStatusList : [ "WAITING",  "OPERATED",  "DONE", ],
            newValue: '',
        }),
        async created() {
            if(!this.value) {
                this.newValue = this.operationStatusList[0];
            } else {
                this.newValue = this.value;
            }
        },
        watch: {
            value(val) {
                this.$emit('input', val);
            },
            newValue(val) {
                this.$emit('input', val);
            },
        },
        methods: {
        }
    }
</script>

