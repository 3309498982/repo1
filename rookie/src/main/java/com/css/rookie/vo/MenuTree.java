package com.css.rookie.vo;

import com.css.rookie.entity.SysMenu;

import java.util.List;

/**
 * <b>类名称：<b/>State <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年9月9日 10:02 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class MenuTree extends SysMenu {

    private String parentMenuName;

    private String text;

    private State state;

    private List<MenuTree> nodes;

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<MenuTree> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuTree> nodes) {
        this.nodes = nodes;
    }
}
