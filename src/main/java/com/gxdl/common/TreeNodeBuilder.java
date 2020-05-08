package com.gxdl.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

	/**
	 * 层级关系构建
	 * @param treeNodes
	 * @param topPid
	 * @return
	 */
public static List<TreeNode> build(List<TreeNode> treeNodes,Integer topPid) {
			List<TreeNode> nodes = new ArrayList<>();
			for (TreeNode n1 : treeNodes) {
				if (n1.getPid()==topPid) {
					nodes.add(n1);
				}
				for (TreeNode n2 : treeNodes) {
					if(n1.getId()==n2.getPid())
						n1.getChildren().add(n2);
				}
			}
			return nodes;
	}
}
