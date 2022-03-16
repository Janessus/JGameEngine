package gameObject.components.animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import templates.Animation;

//TODO
public class AnimatorComponent extends GameObjectComponent
{
	private ArrayList<Animation> animations;
	
	public AnimatorComponent(GameObject parent)
	{
		super(parent);
		animations = new ArrayList<Animation>();
	}
	
	
	@Override
	public void updateComponent()
	{

	}

	
	@Override
	public void drawComponent(Graphics g)
	{
		for(int i = 0; i < animations.size(); i++)
		{
			animations.get(i).paint(g);
		}
	}

	
	@Override
	public void setVisible(boolean visible)
	{
		// TODO Auto-generated method stub
	}
}
