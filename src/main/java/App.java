import entity.City;
import redis.CityCountry;
import service.Service;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Service service = new Service();
        List<City> allCities = service.fetchData(service);
        List<CityCountry> preparedData = service.transformData(allCities);
        service.pushToRedis(preparedData);

        //закроем текущую сессию, чтоб точно делать запрос к БД, а не вытянуть данные из кэша
        service.getSessionFactory().getCurrentSession().close();

        //выбираем случайных 10 id городов
        //так как мы не делали обработку невалидных ситуаций, используй существующие в БД id
        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        service.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        service.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        service.shutdown();
    }
}
