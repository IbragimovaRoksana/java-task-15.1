package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IssueManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    List<String> assigneesUfa = Arrays.asList(new String[]{"Ибрагимова", "Балахонцев"});
    List<String> assigneesMoscow = Arrays.asList(new String[]{"Петров", "Сидорова"});
    List<String> assigneesNovosibirsk = Arrays.asList(new String[]{"Распутина", "Валина"});
    List<String> assigneesStPeter = Arrays.asList(new String[]{"Балахонцева", "Балахонцев", "Ибрагимова"});
    List<String> labelsProjectOne = Arrays.asList(new String[]{"Bug", "Wontfix"});
    List<String> labelsProjectTwo = Arrays.asList(new String[]{"duplicate", "Wontfix", "enhancement", "help wanted"});
    List<String> labelsProjectThree = Arrays.asList(new String[]{"help wanted", "question"});
    List<String> labelsProjectFour = Arrays.asList(new String[]{"Bug"});

    Issue ibragimovaIssue = new Issue(794, "Баг программы", "Привет, нашла баг!",
            "Ибрагимова", assigneesUfa, labelsProjectOne, "Проект1", "Ветка1",
            20, true);
    Issue balakhontsevIssue = new Issue(594, "Баг прогиГоги", "Привет, и я нашел!",
            "Балахонцев", assigneesUfa, labelsProjectOne, "Проект2", "Ветка1",
            19, true);
    Issue balakhontsevaIssue = new Issue(28, "Агугаг", "Ниче вы не нашли!",
            "Балахонцева", assigneesStPeter, labelsProjectOne, "Проект1", "Ветка2",
            28, false);
    Issue petrovIssue = new Issue(333, "Здравствуйте, мой ишу!", "Привет всем!",
            "Петров", assigneesMoscow, labelsProjectFour, "Проект1", "Ветка4",
            19, true);
    Issue ibragimovaHelpIssue = new Issue(795, "Помощь!", "Баг!",
            "Ибрагимова", assigneesUfa, labelsProjectTwo, "Проект2", "Ветка1",
            21, true);
    Issue balakhontsevQuesIssue = new Issue(595, "Уху", "У меня вопрос!",
            "Балахонцев", assigneesNovosibirsk, labelsProjectThree, "Проект3", "Ветка1",
            5, false);
    Issue notFullIssue = new Issue(123, "Not Full Constructor", "Constructor Issue is not full",
            "Сидорова", 6, false);

    @Nested
    public class AllIssues {

        @BeforeEach
        void setIssues() {
            manager.add(ibragimovaIssue);
            manager.add(balakhontsevIssue);
            manager.add(balakhontsevaIssue);
            manager.add(petrovIssue);
            manager.add(balakhontsevQuesIssue);
            manager.add(ibragimovaHelpIssue);
        }

        @Test
        void shouldFindClose() {
            List<Issue> expected = new ArrayList<>();
            expected.add(balakhontsevaIssue);
            expected.add(balakhontsevQuesIssue);
            List<Issue> actual = manager.findClose();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpen() {
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(balakhontsevIssue);
            expected.add(petrovIssue);
            expected.add(ibragimovaHelpIssue);
            List<Issue> actual = manager.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveById() {
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(balakhontsevIssue);
            expected.add(petrovIssue);
            expected.add(balakhontsevQuesIssue);
            expected.add(ibragimovaHelpIssue);
            manager.removeById(28);
            List<Issue> actual = manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldClearAll() {
            List<Issue> expected = new ArrayList<>();
            manager.clearAll();
            List<Issue> actual = manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveAll() {
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(balakhontsevIssue);
            expected.add(balakhontsevaIssue);
            List<Issue> minused = new ArrayList<>();
            minused.add(petrovIssue);
            minused.add(balakhontsevQuesIssue);
            minused.add(ibragimovaHelpIssue);
            manager.removeAll(minused);
            List<Issue> actual = manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortAscending() {
            List<Issue> expected = new ArrayList<>();
            expected.add(balakhontsevQuesIssue);
            expected.add(balakhontsevIssue);
            expected.add(petrovIssue);
            expected.add(ibragimovaIssue);
            expected.add(ibragimovaHelpIssue);
            expected.add(balakhontsevaIssue);
            List<Issue> actual = manager.sortAscending();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDescending() {
            List<Issue> expected = new ArrayList<>();
            expected.add(balakhontsevaIssue);
            expected.add(ibragimovaHelpIssue);
            expected.add(ibragimovaIssue);
            expected.add(petrovIssue);
            expected.add(balakhontsevIssue);
            expected.add(balakhontsevQuesIssue);
            List<Issue> actual = manager.sortDescending();
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseIssue() {
            Issue ibragimovaIssueTest = new Issue(794, "Баг программы", "Привет, нашла баг!",
                    "Ибрагимова", assigneesUfa, labelsProjectOne, "Проект1", "Ветка1",
                    20, false);   //добавляем необходимый для получания Isuue
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssueTest);
            expected.add(balakhontsevIssue);
            expected.add(balakhontsevaIssue);
            expected.add(petrovIssue);
            expected.add(balakhontsevQuesIssue);
            expected.add(ibragimovaHelpIssue);
            manager.closeIssue(794);
            List<Issue> actual = manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenIssue() {
            Issue balakhontsevaIssueTest = new Issue(28, "Агугаг", "Ниче вы не нашли!",
                    "Балахонцева", assigneesStPeter, labelsProjectOne, "Проект1", "Ветка2",
                    28, true);   //добавляем необходимый для получания Isuue
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(balakhontsevIssue);
            expected.add(balakhontsevaIssueTest);
            expected.add(petrovIssue);
            expected.add(balakhontsevQuesIssue);
            expected.add(ibragimovaHelpIssue);
            manager.openIssue(28);
            List<Issue> actual = manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByAuthor() {
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(ibragimovaHelpIssue);
            List<Issue> actual = manager.sortByAuthor("Ибрагимова");
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByAssignee() {
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(balakhontsevIssue);
            expected.add(balakhontsevaIssue);
            expected.add(ibragimovaHelpIssue);
            List<Issue> actual = manager.sortByAssignee("Балахонцев");
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByLabel() {
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(balakhontsevIssue);
            expected.add(balakhontsevaIssue);
            expected.add(petrovIssue);
            List<Issue> actual = manager.sortByLabel("Bug");
            assertEquals(expected, actual);
        }
    }


    @Nested
    public class OneIssue {

        @Test
        void shouldAdd() {
            manager.add(notFullIssue);
            List<Issue> expected = new ArrayList<>();
            expected.add(notFullIssue);
            List<Issue> actual = manager.findAll();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SomeIssuesToSomeIssues {
        @BeforeEach
        void setIssues() {
            manager.add(ibragimovaIssue);
            manager.add(balakhontsevIssue);
            manager.add(balakhontsevaIssue);
        }

        @Test
        void shouldAddAll() {
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(balakhontsevIssue);
            expected.add(balakhontsevaIssue);
            expected.add(petrovIssue);
            expected.add(balakhontsevQuesIssue);
            expected.add(ibragimovaHelpIssue);
            List<Issue> summed = new ArrayList<>();
            summed.add(petrovIssue);
            summed.add(balakhontsevQuesIssue);
            summed.add(ibragimovaHelpIssue);
            manager.addAll(summed);
            List<Issue> actual = manager.findAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldAddSame() {
            manager.add(ibragimovaIssue);
            List<Issue> expected = new ArrayList<>();
            expected.add(ibragimovaIssue);
            expected.add(balakhontsevIssue);
            expected.add(balakhontsevaIssue);
            expected.add(ibragimovaIssue);
            List<Issue> actual = manager.findAll();
            assertEquals(expected, actual);
        }
    }

}
