package io.qameta.allure.jbehave.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Attachment;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.Stack;

import static io.qameta.allure.Allure.getLifecycle;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author charlie (Dmitry Baev).
 */
public class StackSteps {

    private Stack<String> stack = new Stack<>();

    @Given("an empty stack")
    public void createEmptyStack() {
        //written to result JSON, but not attached to Test/Step (and so not appeared in results)
        Allure.addAttachment("My attachment", "My attachment content");

        //not even written to results JSON
        getLifecycle().updateStep(attachment -> attachment.withAttachments(new Attachment().withName("test")));
        stack.clear();
    }

    @Given("a stack with elements")
    public void createStack() {
        stack.clear();
        stack.push("a");
        stack.push("b");
        stack.push("c");
    }

    @When("I add $number elements")
    public void addElements(int elementCount) {
        for (int i = 0; i < elementCount; i++) {
            stack.add((new Integer(i)).toString());
        }
    }

    @When("I clear stack")
    public void clearStack() {
        stack.clear();
    }

    @Then("the stack should have $number elements")
    public void assertElementCount(int elementCount) {
        assertThat(stack)
                .hasSize(elementCount);
    }
}
