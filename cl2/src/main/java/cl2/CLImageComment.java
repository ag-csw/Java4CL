package cl2;

import java.awt.Image;

import cl2a.CLComment;

public class CLImageComment extends CLComment  {


	public CLImageComment(Image data) {
		super(data);
	}

	@Override
	public Image data() {
		return (Image) super.data();
	}
	@Override
	public CLImageComment copy() {
		return new CLImageComment(data());
	}

}
