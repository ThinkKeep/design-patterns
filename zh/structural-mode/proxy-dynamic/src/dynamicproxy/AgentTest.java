package net.sxkeji.shixindesignpattern.dynamicproxy;

import org.junit.Test;

/**
 * Created by zhangshixin on 8/25/2016.
 * Update on ${DATA}
 */
public class AgentTest {
    public static void main(String[] args){
        Star huangBo = new Star("HuangBo");
        Agent agent = new Agent(huangBo);
        // 网上查到，2016年黄渤的片酬达到了 3000W ，我得敲多少年代码额呜呜
        agent.movieShow(1000000000);
        agent.tvShow(5);
    }
}