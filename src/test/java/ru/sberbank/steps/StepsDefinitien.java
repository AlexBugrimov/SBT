package ru.sberbank.steps;

import cucumber.api.java.ru.*;
import org.junit.Assert;
import ru.sberbank.entities.Driver;
import ru.sberbank.pages.MainPage;
import java.util.List;

public class StepsDefinitien {
    private Driver driver;
    private MainPage page;

    @Дано("^Пользователь открывает браузер$")
    public void openBrowser() throws Throwable {
        driver = new Driver();
    }

    @И("^переходит на страницу \"([^\"]*)\"$")
    public void goToPage(String site) throws Throwable {
        driver.openSite(site);
        page = new MainPage(driver.get());
    }

    @Когда("^Пользователь нажимает кнопку поиска региона$")
    public void clickButtonSearch() throws Throwable {
        page.clickLinkRegionList();
    }

    @Тогда("^Он вводит в поле поиска значение \"([^\"]*)\"$")
    public void inputInField(String value) throws Throwable {
        page.inputValueInFieldSearchRegion(value);
    }

    @И("^выбирает его из появившегося списка$")
    public void select() throws Throwable {
        page.selectValueRegion();
    }

    @Если("^на основной странице выбран регион \"([^\"]*)\"$")
    public void assertRegion(String region) throws Throwable {
        Assert.assertTrue("Регион не корректный", page.valueAttributRegion("title").equalsIgnoreCase(region));
    }

    @Тогда("^Он прокручивает скролл к нижнему колонтитулу$")
    public void scrollTo() throws Throwable {
        page.scrollToSocialList();
    }

    @И("^проверяет иконки социальных сетей:$")
    public void checkIcons(List<String> resources) throws Throwable {
        List<String> items = page.valuesAttributSocialItem("href");
        for (String item : items) {
            boolean isContains = false;
            for (String resource : resources) {
                if (item.contains(resource)) {
                    isContains = true;
                    break;
                }
            }
            Assert.assertTrue("Иконки \'" + item + "\' нет", isContains);
        }
    }

    @Дано("^Пользователь закрывает браузер$")
    public void closeBrowser() throws Throwable {
        driver.closeBrowser();
    }
}
