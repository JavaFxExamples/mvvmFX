/*******************************************************************************
 * Copyright 2015 Alexander Casall, Manuel Mauky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.saxsys.mvvmfx.resourcebundle.global;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.testingutils.JfxToolkitExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test global resource bundles. See https://github.com/sialcasa/mvvmFX/issues/234.
 * 
 * @author manuel.mauky
 */
@ExtendWith(JfxToolkitExtension.class)
public class GlobalResourceBundleTest {
	
	
	private ResourceBundle global;
	private ResourceBundle other;
	private ResourceBundle third;
	
	@BeforeEach
	public void setup(){
		MvvmFX.setGlobalResourceBundle(null);
		global = ResourceBundle.getBundle(this.getClass().getPackage().getName() + ".global");
		other = ResourceBundle.getBundle(this.getClass().getPackage().getName() + ".other");
		third = ResourceBundle.getBundle(this.getClass().getPackage().getName() + ".third");
	}
	
	@AfterEach
	public void tearDown() {
		MvvmFX.setGlobalResourceBundle(null);
	}
	
	@Test
	public void test() {
		MvvmFX.setGlobalResourceBundle(global);

		final ViewTuple<TestView, TestViewModel> viewTuple = FluentViewLoader.fxmlView(TestView.class).resourceBundle(other).load();
		final TestView codeBehind = viewTuple.getCodeBehind();
	
		assertThat(codeBehind.resources).isNotNull();
		
		assertThat(codeBehind.global_label.getText()).isEqualTo("global");
		assertThat(codeBehind.other_label.getText()).isEqualTo("other");
		
		// both "global" and "other" are containing the key "label". 
		// in this case "other" has the higher priority and overwrites the value defined in "global"
		assertThat(codeBehind.label.getText()).isEqualTo("other");
	}

	/**
	 * It's possible to use more then one resourceBundle with {@link FluentViewLoader}.
	 */
	@Test
	public void testThreeResourceBundles() {
		MvvmFX.setGlobalResourceBundle(global);

		final ViewTuple<TestView, TestViewModel> viewTuple = FluentViewLoader.fxmlView(TestView.class)
				.resourceBundle(other)
				.resourceBundle(third)
				.load();
		final TestView codeBehind = viewTuple.getCodeBehind();

		assertThat(codeBehind.resources).isNotNull();

		assertThat(codeBehind.global_label.getText()).isEqualTo("global");
		assertThat(codeBehind.other_label.getText()).isEqualTo("other");

		// the "third" bundle was added last in the fluent API.
		// for this reason it has the highest priority and overwrites other values
		assertThat(codeBehind.label.getText()).isEqualTo("third");

		assertThat(codeBehind.resources.getString("third_label")).isEqualTo("third");

	}
	
}
