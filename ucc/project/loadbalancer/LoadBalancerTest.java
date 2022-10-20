package ucc.project.loadbalancer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadBalancerTest {

        @Test
        public void shouldGetServerList(){
            LoadBalancer lb = new LoadBalancer();
            lb.getServerList();
        }

        @Test
        public void shouldPickServer(){

        }

        @Test
        public void shouldGetServerAddress(){

        }

}