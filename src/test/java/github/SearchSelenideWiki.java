package github;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

//задание 1
//Есть ли разница между $("h1 div"); и $("h1").$("div"); - может ли привести к тому что, поиск найдёт разные элементы?
//Если может - приведите пример, когда
//
// Я искала примеры на разных сайтах, но что-то  мне не попадалось страничка, где внутри h1 был бы ещё div.
// Но мне очень понравися пример коллеги, можно я его сюда перепишу?

//<html>
//<body>
//<h1>Просто h1</h1>
//<h1><div>h1 с div</div></h1>
//</body>
//</html>

//Различие есть: $("h1 div") будет искать первый элемент h1 с div
//$("h1").$("div") будет искать первый h1 и первый div в нем.


//задание 2

public class SearchSelenideWiki {
    @Test
    void shouldFindeSelenideWiki(){
        // открыть страницу github.com
        open("https://github.com/");

        // ввести в поле поиска Selenide и нажать Enter
        $("[data-test-selector=nav-search-input]").setValue("Selenide").pressEnter();

        // нажимаем на линк от первого результата поиска
        $(".repo-list li").$("a").click();

        // chek: в заголовке встречается selenide/selenide
        $("h1").shouldHave(text("selenide / selenide"));

        // Переходим в раздел Wiki проекта
        $("#wiki-tab").click();
        $(".wiki-more-pages-link button").pressEnter();

        // Убеждаемся, что в списке страниц (Pages) есть страница SoftAssertions, кликаем на неё
        $(".Layout-main").scrollTo().$(byText("Soft assertions")).click();
        //$(".js-wiki-more-pages-link").click();
        $(byText("SoftAssertions")).click();

        // проверяем ,что внутри SoftAssertions есть пример кода для JUnit5
        $("#wiki-body").shouldHave(text("Using JUnit5 extend test class:"));

    }
}
