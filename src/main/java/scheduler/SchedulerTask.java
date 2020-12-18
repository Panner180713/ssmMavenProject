package scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @Author chenshoukai
 * @Date 2020/03/12 9:27
 */
@Component
public class SchedulerTask {

    @Scheduled(fixedRate = 1000*5)
    public void runInSeconds(){
        System.out.println("---->>>>秒级定时器<<<<----");
    }
}
