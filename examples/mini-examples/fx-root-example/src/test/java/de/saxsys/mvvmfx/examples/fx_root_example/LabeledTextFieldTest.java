package de.saxsys.mvvmfx.examples.fx_root_example;

import static eu.lestard.assertj.javafx.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LabeledTextFieldTest {
	
	private LabeledTextFieldViewModel viewModel;
	
	@BeforeEach
	public void setup() {
		viewModel = new LabeledTextFieldViewModel();
	}
	
	@Test
	public void testButtonDisabled() {
		
		viewModel.inputTextProperty().set("");
		assertThat(viewModel.buttonDisabledProperty()).isTrue();
		
		
		viewModel.inputTextProperty().set("hello");
		assertThat(viewModel.buttonDisabledProperty()).isFalse();
		
		viewModel.inputTextProperty().set(null);
		assertThat(viewModel.buttonDisabledProperty()).isTrue();
	}
	
	/**
	 * When the user presses the action button, the entered text should be used as new label text. The input textfield
	 * should be cleared.
	 */
	@Test
	public void testOnAction() {
		assertThat(viewModel.labelTextProperty()).hasValue("default");
		
		viewModel.inputTextProperty().set("hello");
		
		assertThat(viewModel.labelTextProperty()).hasValue("default"); // label has still the old value
		
		viewModel.changeLabel();
		
		assertThat(viewModel.labelTextProperty()).hasValue("hello"); // now the label has the new value
		
		assertThat(viewModel.inputTextProperty()).hasValue("");
	}
}
