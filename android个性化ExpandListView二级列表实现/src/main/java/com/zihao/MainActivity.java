package com.zihao;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.app.Activity;

/**
 * 显示内容做的不错,但是所有标记的技法都是错误的,除了修改一级菜单的图片.
 */

public class MainActivity extends Activity {

	// 这个数组是用来存储一级item的点击次数的，根据点击次数设置一级标签的选中、为选中状态
	private int[] group_checked = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	// 用来标识是否设置二級item背景色为绿色,初始值为-1既为选中状态
	private int child_groupId = -1;
	private int child_childId = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);
		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list);
		// 设置默认图标为不显示状态
		expandableListView.setGroupIndicator(null);
		// 为列表绑定数据源
		expandableListView.setAdapter(adapter);
		// 设置一级item点击的监听器
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
//				不就是实时的改为对立面的值,然后重新的刷新,参见底下的设置图片的方式
				group_checked[groupPosition] = group_checked[groupPosition] + 1;
				// 刷新界面
				((BaseExpandableListAdapter) adapter).notifyDataSetChanged();
				return false;
			}
		});

		// 设置二级item点击的监听器
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// 将被点击的一丶二级标签的位置记录下来
//				child_groupId = groupPosition;
//				child_childId = childPosition;
//				// 刷新界面
//				((BaseExpandableListAdapter) adapter).notifyDataSetChanged();
				return false;
			}
		});
	}

	final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
		// 一级标签上的logo图片数据源
		// 一级标签上的标题数据源
		private String[] group_title_arry_1 = new String[] { "颈椎测试", "腰部测试" };
		// 子视图显示文字
		private String[][] child_text_array_2 = new String[][] {
				{ "是否经常感到左臂疼痛？", "是否经常熬夜？", "您的踝关节有刺痛的现象吗？", "是否经常用凉水洗头？" ,"请保重身体"},
				{ "是否经常感到左臂疼痛？111", "是否经常熬夜？111", "您的踝关节有刺痛的现象吗？111", "是否经常用凉水洗头？111" }};
		// 一级标签上的状态图片数据源
		int[] group_state_array_1_photo = new int[] { R.drawable.group_down, R.drawable.group_up };

		// 重写ExpandableListAdapter中的各个方法
		/**
		 * 获取一级标签总数
		 */
		@Override
		public int getGroupCount() {
			return group_title_arry_1.length;
		}

		/**
		 * 获取一级标签内容
		 */
		@Override
		public Object getGroup(int groupPosition) {
			return group_title_arry_1[groupPosition];
		}

		/**
		 * 获取一级标签的ID
		 */
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		/**
		 * 获取一级标签下二级标签的总数
		 */
		@Override
		public int getChildrenCount(int groupPosition) {
			return child_text_array_2[groupPosition].length;
		}

		/**
		 * 获取一级标签下二级标签的内容
		 */
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return child_text_array_2[groupPosition][childPosition];
		}

		/**
		 * 获取二级标签的ID
		 */
		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		/**
		 * 指定位置相应的组视图
		 */
		@Override
		public boolean hasStableIds() {
			return true;
		}

		/**
		 * 对一级标签进行设置
		 */
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// 为视图对象指定布局
			convertView = (LinearLayout) LinearLayout.inflate(getBaseContext(),
					R.layout.group_layout, null);

			RelativeLayout myLayout = (RelativeLayout) convertView
					.findViewById(R.id.group_layout);

			/**
			 * 声明视图上要显示的控件
			 */
			// 新建一个TextView对象，用来显示一级标签上的标题信息
			TextView group_title = (TextView) convertView
					.findViewById(R.id.group_title);
			// 新建一个TextView对象，用来显示一级标签上的大体描述的信息
			ImageView group_state = (ImageView) convertView
					.findViewById(R.id.group_state);
			/**
			 * 设置相应控件的内容
			 */
			// 设置标题上的文本信息
			group_title.setText(group_title_arry_1[groupPosition]);
			// 设置整体描述上的文本信息

			if (group_checked[groupPosition] % 2 == 1) {
				// 设置默认的图片是选中状态
				group_state.setBackgroundResource(group_state_array_1_photo[1]);
				myLayout.setBackgroundResource(R.drawable.text_item_top_bg);	//注意展开的时候边角是有区别的.

			} else {
				for (int test : group_checked) {
					if (test == 0 || test % 2 == 0) {
						myLayout.setBackgroundResource(R.drawable.text_item_bg);
						// 设置默认的图片是未选中状态
						group_state.setBackgroundResource(group_state_array_1_photo[0]);
					}
				}
			}
			// 返回一个布局对象
			return convertView;
		}

		/**
		 * 对一级标签下的二级标签进行设置
		 */
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			// 为视图对象指定布局,此处写死之后是错误的.应该是动态写入的.
			if (childPosition == 3) {
				convertView = (RelativeLayout) RelativeLayout.inflate(
						getBaseContext(), R.layout.child_s_layout, null);
			} else {
				convertView = (RelativeLayout) RelativeLayout.inflate(
						getBaseContext(), R.layout.child_layout, null);
			}
			/**
			 * 声明视图上要显示的控件
			 */
			// 新建一个TextView对象，用来显示具体内容
			TextView child_text = (TextView) convertView
					.findViewById(R.id.child_text);
			/**
			 * 设置相应控件的内容,只是显示内容
			 */
			// 设置要显示的文本信息
			child_text.setText(child_text_array_2[groupPosition][childPosition]);
			return convertView;
		}

		/**
		 * 当选择子节点的时候，调用该方法
		 */
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

	};

}