package com.ebaonet.pharmacy.sdk.IndexContent.firstpage;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * 就诊费用明细
 * 
 * @author ning.yang
 *
 */
public class SysFirstPageListInfo extends BaseEntity {

	private List<SysFirstPage> sysFirstPageList;

	public List<SysFirstPage> getSysFirstPageList() {
		return sysFirstPageList;
	}

	public void setSysFirstPageList(List<SysFirstPage> sysFirstPageList) {
		this.sysFirstPageList = sysFirstPageList;
	}
}
