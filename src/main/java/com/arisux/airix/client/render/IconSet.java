//package com.arisux.airix.client.render;
//
////TODO: This needs to be fixed. IIcons were removed in 1.8. They need to be added back, or we need to find a better alternative.
//public class IconSet
//{
//	public IIcon icon, top, bottom, front, back, left, right;
//	public String iconRes, topRes, bottomRes, frontRes, backRes, leftRes, rightRes;
//
//	public IconSet(String icon)
//	{
//		this(icon, null, null, null, null, null, null);
//	}
//
//	public IconSet(String icon, String top, String bottom, String front, String back, String left, String right)
//	{
//		this.iconRes = icon;
//		this.topRes = top;
//		this.bottomRes = bottom;
//		this.frontRes = front;
//		this.backRes = back;
//		this.leftRes = left;
//		this.rightRes = right;
//	}
//
//	public void registerIcons(IIconRegister register)
//	{
//		this.icon = (register.registerIcon(iconRes));
//		this.top = (register.registerIcon(topRes));
//		this.bottom = (register.registerIcon(bottomRes));
//		this.front = (register.registerIcon(frontRes));
//		this.back = (register.registerIcon(backRes));
//		this.left = (register.registerIcon(leftRes));
//		this.right = (register.registerIcon(rightRes));
//	}
//}