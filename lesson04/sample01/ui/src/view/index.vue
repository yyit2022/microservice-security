<template>

    <div>

        <h1>书籍清单 APP</h1>


<span @click="$store.commit('add')">{{$store.state.count}}</span>

        <button>加载书籍</button>

        

        <div>

            <ul>
                <li>Acing the System Design Interview</li>
                <li>Designing Microservices</li>
                <li>Tiny CSS Projects</li>
                <li>gRPC Microservices in Go</li>
            </ul>

        </div>

    </div>

</template>

<script setup lang="ts">


</script>