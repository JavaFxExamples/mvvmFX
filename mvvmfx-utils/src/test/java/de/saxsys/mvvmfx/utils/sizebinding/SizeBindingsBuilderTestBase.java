/*******************************************************************************
 * Copyright 2014 Manuel Mauky
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
package de.saxsys.mvvmfx.utils.sizebinding;

import static org.assertj.core.api.Assertions.assertThat;

import de.saxsys.mvvmfx.testingutils.JfxToolkitExtension;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.util.reflection.Whitebox;


@ExtendWith(JfxToolkitExtension.class)
public abstract class SizeBindingsBuilderTestBase {
	
	protected static final double SIZEVAL = 100d;
	
	protected Region fromRegion;
	
	protected Control fromControl;
	
	protected Rectangle fromRectangle;
	
	protected ImageView fromImageView;
	
	@BeforeEach
	public void setup() {
		fromRegion = new Region();
		mockSize(fromRegion);
		fromControl = new ScrollPane();
		mockSize(fromControl);
		fromRectangle = new Rectangle();
		mockSize(fromRectangle);
		
		fromImageView = new ImageView();
		fromImageView.setFitWidth(SIZEVAL);
		fromImageView.setFitHeight(SIZEVAL);
	}
	
	
	/**
	 * Mock the internal storage of the width and height. This is a workaround because we can't use PowerMock in this
	 * case due to conflicts with Javafx 8 initialization of controls.
	 */
	private void mockSize(Object object) {
		Whitebox.setInternalState(object, "width", new ReadOnlyDoubleWrapper(SIZEVAL));
		Whitebox.setInternalState(object, "height", new ReadOnlyDoubleWrapper(SIZEVAL));
	}
	
	
	protected void assertCorrectSize(Rectangle rectangle) {
		assertCorrectHeight(rectangle);
		assertCorrectWidth(rectangle);
	}
	
	
	protected void assertCorrectSize(Control control) {
		assertCorrectHeight(control);
		assertCorrectWidth(control);
	}
	
	protected void assertCorrectSize(Region region) {
		assertCorrectHeight(region);
		assertCorrectWidth(region);
	}
	
	protected void assertCorrectSize(ImageView imageView) {
		assertCorrectHeight(imageView);
		assertCorrectWidth(imageView);
	}
	
	
	protected void assertCorrectHeight(Rectangle rectangle) {
		assertThat(rectangle.getHeight()).isEqualTo(SIZEVAL);
	}
	
	protected void assertCorrectHeight(Control control) {
		assertThat(control.getMaxHeight()).isEqualTo(SIZEVAL);
		assertThat(control.getMinHeight()).isEqualTo(SIZEVAL);
	}
	
	protected void assertCorrectHeight(Region region) {
		assertThat(region.getMaxHeight()).isEqualTo(SIZEVAL);
		assertThat(region.getMinHeight()).isEqualTo(SIZEVAL);
	}
	
	protected void assertCorrectHeight(ImageView imageView) {
		assertThat(imageView.getFitHeight()).isEqualTo(SIZEVAL);
	}
	
	protected void assertCorrectWidth(Rectangle rectangle) {
		assertThat(rectangle.getWidth()).isEqualTo(SIZEVAL);
	}
	
	
	protected void assertCorrectWidth(Control control) {
		assertThat(control.getMaxWidth()).isEqualTo(SIZEVAL);
		assertThat(control.getMinWidth()).isEqualTo(SIZEVAL);
	}
	
	protected void assertCorrectWidth(Region region) {
		assertThat(region.getMaxWidth()).isEqualTo(SIZEVAL);
		assertThat(region.getMinWidth()).isEqualTo(SIZEVAL);
	}
	
	protected void assertCorrectWidth(ImageView imageView) {
		assertThat(imageView.getFitWidth()).isEqualTo(SIZEVAL);
	}
	
}
