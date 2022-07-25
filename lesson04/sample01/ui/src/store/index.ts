import { createStore } from 'vuex'

const store = createStore({
    state: {
        count:10
    },
    getters: {},
    mutations: {
        add(state){
            state.count++
        }
    },
    actions: {},
    modules: {}
})

export default store