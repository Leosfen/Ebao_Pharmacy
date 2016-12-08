package com.ebaonet.pharmacy.entity.drug.sort;

import com.ebaonet.pharmacy.entity.BaseEntity;

import java.util.List;

/**
 * 药品说明书查询
 */
public class DrugDispEntry extends BaseEntity {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static class Drugbackbean {


        /**
         * status : 1
         * pageno : 1
         * pagesize : 1
         * totalpage : 1
         * totals : 1
         * datas : [{"COMPANY":"吉林省吴太感康药业有限公司","INTERACTION":"0.1.与其他解热镇痛药同用，可增加肾毒性的危险。2.本品不宜与氯霉素、巴比妥类（如苯巴比妥）等并用。3.如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。","ORG_SPEC_URL":"http://drugs.medlive.cn/drugref/html/15515.shtml","PRODUCT_NAME":"感康","PHARMACO":"药理作用：对乙酰氨基酚能抑制前列腺素合成，有解热镇痛的作用；金刚烷胺可抗\u201c亚一甲型\u201d流感病毒，抑制病毒繁殖；咖啡因为中枢兴奋药，能增强对乙酰氨基酚的解热镇痛效果，并能减轻其他药物所致的嗜睡、头晕等中枢抑制作用；马来酸氯苯那敏为抗过敏药，能减轻流涕、鼻塞、打喷嚏等症状；人工牛黄具有解热、镇惊作用。上述诸药配伍制成复方，可增强解热、镇痛效果，解除或改善感冒所引起的各种症状。","DOSAGE":"口服。成人，一次1片，一日2次。","ENG_NAME":"CompoundParacetamolandAmantadineHydrochlorideTablets","ORG_SPEC":"用药参考","APPROVAL_NO":"国药准字H22026193","STAD_NAME":"复方氨酚烷胺片","INDICATION":"适用于缓解普通感冒及流行性感冒引起的发热、头痛、四肢酸痛、打喷嚏、流鼻涕、鼻塞、咽痛等症状。","ELEMENT":"成份：本品为复方制剂，每片含对乙酰氨基酚250毫克，盐酸金刚烷胺100毫克，人工牛黄10毫克，咖啡因15毫克，马来酸氯苯那敏2毫克。辅料为：淀粉、糖粉、硬脂酸镁。","SPEC":"复方。","FUNCTION_TYPE":"本品为感冒用药类非处方药品。","VALIDITY":"36个月","INS_ID":"SMS014709","CAUTION":"【禁忌】严重肝肾功能不全者禁用","CHN_SPELL":"FuFangAnFenWanAnPian","ROW_ID":1,"PACK":"双铝箔包装，12片/板/盒","CHARACTER":"本品为淡黄色圆形片，味苦。","STORE":"密闭，在阴凉干燥处保存。","ADVERSE":"有时有轻度头晕、乏力、恶心、上腹不适、口干、食欲缺乏和皮疹等，可自行恢复。","EXECUTIVE_STAD":"WS<sub>1<\/sub>-XG-015-2002","NOTES":"1.用药3-7天，症状未缓解，请咨询医师或药师。2.服用本品期间不得饮酒或含有酒精的饮料。3.不能同时服用与本品成份相似的其他抗感冒药。4.肝功能不全、肾功能不全、脑血管病史、精神病史或癫痫病史患者慎用。5.前列腺肥大、青光眼等患者以及老年人应在医师指导下使用。6.孕妇及哺乳期妇女慎用。7.服药期间不得驾驶机、车、船、从事高空作业、机械作业及操作精密仪器。8.如服用过量或出现严重不良反应，应立即就医。9.对本品过敏者禁用，过敏体质者慎用。10.本品性状发生改变时禁止使用。11.请将本品放在儿童不能接触的地方。12.如正在使用其他药品，使用本品前请咨询医师或药师。","TABOO":"严重肝肾功能不全者禁用。"}]
         * showfield : ["ins_id","title","remark","caution","warning","stad_name","product_name","eng_name","chn_spell","element","character","elem_char","function_type","radiate","radioactive","inoculate","indication","function","spec","effect","immune","dosage","absorb","adverse","taboo","notes","gravida_use","child_use","aged_use","interaction","ecessive","clinical","pharmaco","kinetics","store","pack","validity","executive_stad","approval_no","import_no","pack_no","company","pack_company","com_address","com_code","com_phone","com_url","org_spec","org_spec_url"]
         * cannotshowfield : []
         */

        private String status;
        private int pageno;
        private int pagesize;
        private int totalpage;
        private int totals;
        /**
         * COMPANY : 吉林省吴太感康药业有限公司
         * INTERACTION : 0.1.与其他解热镇痛药同用，可增加肾毒性的危险。2.本品不宜与氯霉素、巴比妥类（如苯巴比妥）等并用。3.如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。
         * ORG_SPEC_URL : http://drugs.medlive.cn/drugref/html/15515.shtml
         * PRODUCT_NAME : 感康
         * PHARMACO : 药理作用：对乙酰氨基酚能抑制前列腺素合成，有解热镇痛的作用；金刚烷胺可抗“亚一甲型”流感病毒，抑制病毒繁殖；咖啡因为中枢兴奋药，能增强对乙酰氨基酚的解热镇痛效果，并能减轻其他药物所致的嗜睡、头晕等中枢抑制作用；马来酸氯苯那敏为抗过敏药，能减轻流涕、鼻塞、打喷嚏等症状；人工牛黄具有解热、镇惊作用。上述诸药配伍制成复方，可增强解热、镇痛效果，解除或改善感冒所引起的各种症状。
         * DOSAGE : 口服。成人，一次1片，一日2次。
         * ENG_NAME : CompoundParacetamolandAmantadineHydrochlorideTablets
         * ORG_SPEC : 用药参考
         * APPROVAL_NO : 国药准字H22026193
         * STAD_NAME : 复方氨酚烷胺片
         * INDICATION : 适用于缓解普通感冒及流行性感冒引起的发热、头痛、四肢酸痛、打喷嚏、流鼻涕、鼻塞、咽痛等症状。
         * ELEMENT : 成份：本品为复方制剂，每片含对乙酰氨基酚250毫克，盐酸金刚烷胺100毫克，人工牛黄10毫克，咖啡因15毫克，马来酸氯苯那敏2毫克。辅料为：淀粉、糖粉、硬脂酸镁。
         * SPEC : 复方。
         * FUNCTION_TYPE : 本品为感冒用药类非处方药品。
         * VALIDITY : 36个月
         * INS_ID : SMS014709
         * CAUTION : 【禁忌】严重肝肾功能不全者禁用
         * CHN_SPELL : FuFangAnFenWanAnPian
         * ROW_ID : 1
         * PACK : 双铝箔包装，12片/板/盒
         * CHARACTER : 本品为淡黄色圆形片，味苦。
         * STORE : 密闭，在阴凉干燥处保存。
         * ADVERSE : 有时有轻度头晕、乏力、恶心、上腹不适、口干、食欲缺乏和皮疹等，可自行恢复。
         * EXECUTIVE_STAD : WS<sub>1</sub>-XG-015-2002
         * NOTES : 1.用药3-7天，症状未缓解，请咨询医师或药师。2.服用本品期间不得饮酒或含有酒精的饮料。3.不能同时服用与本品成份相似的其他抗感冒药。4.肝功能不全、肾功能不全、脑血管病史、精神病史或癫痫病史患者慎用。5.前列腺肥大、青光眼等患者以及老年人应在医师指导下使用。6.孕妇及哺乳期妇女慎用。7.服药期间不得驾驶机、车、船、从事高空作业、机械作业及操作精密仪器。8.如服用过量或出现严重不良反应，应立即就医。9.对本品过敏者禁用，过敏体质者慎用。10.本品性状发生改变时禁止使用。11.请将本品放在儿童不能接触的地方。12.如正在使用其他药品，使用本品前请咨询医师或药师。
         * TABOO : 严重肝肾功能不全者禁用。
         */

        private List<DatasBean> datas;
        private List<String> showfield;
        private List<?> cannotshowfield;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getPageno() {
            return pageno;
        }

        public void setPageno(int pageno) {
            this.pageno = pageno;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public int getTotals() {
            return totals;
        }

        public void setTotals(int totals) {
            this.totals = totals;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public List<String> getShowfield() {
            return showfield;
        }

        public void setShowfield(List<String> showfield) {
            this.showfield = showfield;
        }

        public List<?> getCannotshowfield() {
            return cannotshowfield;
        }

        public void setCannotshowfield(List<?> cannotshowfield) {
            this.cannotshowfield = cannotshowfield;
        }

        public static class DatasBean {

            private int ROW_ID;
            private String STAD_NAME;
            private String INS_ID;
            private String TITLE;
            private String REMARK;
            private String CAUTION;
            private String WARNING;
            private String PRODUCT_NAME;
            private String ENG_NAME;
            private String CHN_SPELL;
            private String ELEMENT;
            private String CHARACTER;
            private String ELEM_CHAR;
            private String FUNCTION_TYPE;
            private String RADIATE;
            private String RADIOACTIVE;
            private String INOCULATE;
            private String INDICATION;
            private String FUNCTION;
            private String SPEC;
            private String EFFECT;
            private String IMMUNE;
            private String DOSAGE;
            private String ABSORB;
            private String ADVERSE;
            private String TABOO;
            private String NOTES;
            private String GRAVIDA_USE;
            private String CHILD_USE;
            private String AGED_USE;
            private String INTERACTION;
            private String EXCESSIVE;
            private String CLINICAL;
            private String PHARMACO;
            private String KINETICS;
            private String STORE;
            private String PACK;
            private String VALIDITY;
            private String EXECUTIVE_STAD;
            private String APPROVAL_NO;
            private String IMPORT_NO;
            private String PACK_NO;
            private String COMPANY;
            private String PACK_COMPANY;
            private String COM_ADDRESS;
            private String COM_CODE;
            private String COM_PHONE;
            private String COM_URL;
            private String ORG_SPEC;
            private String ORG_SPEC_URL;

            public String getCOMPANY() {
                return COMPANY;
            }

            public void setCOMPANY(String COMPANY) {
                this.COMPANY = COMPANY;
            }

            public String getINTERACTION() {
                return INTERACTION;
            }

            public void setINTERACTION(String INTERACTION) {
                this.INTERACTION = INTERACTION;
            }

            public String getORG_SPEC_URL() {
                return ORG_SPEC_URL;
            }

            public void setORG_SPEC_URL(String ORG_SPEC_URL) {
                this.ORG_SPEC_URL = ORG_SPEC_URL;
            }

            public String getPRODUCT_NAME() {
                return PRODUCT_NAME;
            }

            public void setPRODUCT_NAME(String PRODUCT_NAME) {
                this.PRODUCT_NAME = PRODUCT_NAME;
            }

            public String getPHARMACO() {
                return PHARMACO;
            }

            public void setPHARMACO(String PHARMACO) {
                this.PHARMACO = PHARMACO;
            }

            public String getDOSAGE() {
                return DOSAGE;
            }

            public void setDOSAGE(String DOSAGE) {
                this.DOSAGE = DOSAGE;
            }

            public String getENG_NAME() {
                return ENG_NAME;
            }

            public void setENG_NAME(String ENG_NAME) {
                this.ENG_NAME = ENG_NAME;
            }

            public String getORG_SPEC() {
                return ORG_SPEC;
            }

            public void setORG_SPEC(String ORG_SPEC) {
                this.ORG_SPEC = ORG_SPEC;
            }

            public String getAPPROVAL_NO() {
                return APPROVAL_NO;
            }

            public void setAPPROVAL_NO(String APPROVAL_NO) {
                this.APPROVAL_NO = APPROVAL_NO;
            }

            public String getSTAD_NAME() {
                return STAD_NAME;
            }

            public void setSTAD_NAME(String STAD_NAME) {
                this.STAD_NAME = STAD_NAME;
            }

            public String getINDICATION() {
                return INDICATION;
            }

            public void setINDICATION(String INDICATION) {
                this.INDICATION = INDICATION;
            }

            public String getELEMENT() {
                return ELEMENT;
            }

            public void setELEMENT(String ELEMENT) {
                this.ELEMENT = ELEMENT;
            }

            public String getSPEC() {
                return SPEC;
            }

            public void setSPEC(String SPEC) {
                this.SPEC = SPEC;
            }

            public String getFUNCTION_TYPE() {
                return FUNCTION_TYPE;
            }

            public void setFUNCTION_TYPE(String FUNCTION_TYPE) {
                this.FUNCTION_TYPE = FUNCTION_TYPE;
            }

            public String getVALIDITY() {
                return VALIDITY;
            }

            public void setVALIDITY(String VALIDITY) {
                this.VALIDITY = VALIDITY;
            }

            public String getINS_ID() {
                return INS_ID;
            }

            public void setINS_ID(String INS_ID) {
                this.INS_ID = INS_ID;
            }

            public String getCAUTION() {
                return CAUTION;
            }

            public void setCAUTION(String CAUTION) {
                this.CAUTION = CAUTION;
            }

            public String getCHN_SPELL() {
                return CHN_SPELL;
            }

            public void setCHN_SPELL(String CHN_SPELL) {
                this.CHN_SPELL = CHN_SPELL;
            }

            public int getROW_ID() {
                return ROW_ID;
            }

            public void setROW_ID(int ROW_ID) {
                this.ROW_ID = ROW_ID;
            }

            public String getPACK() {
                return PACK;
            }

            public void setPACK(String PACK) {
                this.PACK = PACK;
            }

            public String getCHARACTER() {
                return CHARACTER;
            }

            public void setCHARACTER(String CHARACTER) {
                this.CHARACTER = CHARACTER;
            }

            public String getSTORE() {
                return STORE;
            }

            public void setSTORE(String STORE) {
                this.STORE = STORE;
            }

            public String getADVERSE() {
                return ADVERSE;
            }

            public void setADVERSE(String ADVERSE) {
                this.ADVERSE = ADVERSE;
            }

            public String getEXECUTIVE_STAD() {
                return EXECUTIVE_STAD;
            }

            public void setEXECUTIVE_STAD(String EXECUTIVE_STAD) {
                this.EXECUTIVE_STAD = EXECUTIVE_STAD;
            }

            public String getNOTES() {
                return NOTES;
            }

            public void setNOTES(String NOTES) {
                this.NOTES = NOTES;
            }

            public String getTABOO() {
                return TABOO;
            }

            public void setTABOO(String TABOO) {
                this.TABOO = TABOO;
            }

            public String getWARNING() {
                return WARNING;
            }

            public void setWARNING(String WARNING) {
                this.WARNING = WARNING;
            }

            public String getABSORB() {
                return ABSORB;
            }

            public void setABSORB(String ABSORB) {
                this.ABSORB = ABSORB;
            }

            public String getAGED_USE() {
                return AGED_USE;
            }

            public void setAGED_USE(String AGED_USE) {
                this.AGED_USE = AGED_USE;
            }

            public String getCHILD_USE() {
                return CHILD_USE;
            }

            public void setCHILD_USE(String CHILD_USE) {
                this.CHILD_USE = CHILD_USE;
            }

            public String getCLINICAL() {
                return CLINICAL;
            }

            public void setCLINICAL(String CLINICAL) {
                this.CLINICAL = CLINICAL;
            }

            public String getCOM_ADDRESS() {
                return COM_ADDRESS;
            }

            public void setCOM_ADDRESS(String COM_ADDRESS) {
                this.COM_ADDRESS = COM_ADDRESS;
            }

            public String getCOM_CODE() {
                return COM_CODE;
            }

            public void setCOM_CODE(String COM_CODE) {
                this.COM_CODE = COM_CODE;
            }

            public String getCOM_PHONE() {
                return COM_PHONE;
            }

            public void setCOM_PHONE(String COM_PHONE) {
                this.COM_PHONE = COM_PHONE;
            }

            public String getCOM_URL() {
                return COM_URL;
            }

            public void setCOM_URL(String COM_URL) {
                this.COM_URL = COM_URL;
            }

            public String getEFFECT() {
                return EFFECT;
            }

            public void setEFFECT(String EFFECT) {
                this.EFFECT = EFFECT;
            }

            public String getELEM_CHAR() {
                return ELEM_CHAR;
            }

            public void setELEM_CHAR(String ELEM_CHAR) {
                this.ELEM_CHAR = ELEM_CHAR;
            }

            public String getEXCESSIVE() {
                return EXCESSIVE;
            }

            public void setEXCESSIVE(String EXCESSIVE) {
                this.EXCESSIVE = EXCESSIVE;
            }

            public String getFUNCTION() {
                return FUNCTION;
            }

            public void setFUNCTION(String FUNCTION) {
                this.FUNCTION = FUNCTION;
            }

            public String getGRAVIDA_USE() {
                return GRAVIDA_USE;
            }

            public void setGRAVIDA_USE(String GRAVIDA_USE) {
                this.GRAVIDA_USE = GRAVIDA_USE;
            }

            public String getIMMUNE() {
                return IMMUNE;
            }

            public void setIMMUNE(String IMMUNE) {
                this.IMMUNE = IMMUNE;
            }

            public String getIMPORT_NO() {
                return IMPORT_NO;
            }

            public void setIMPORT_NO(String IMPORT_NO) {
                this.IMPORT_NO = IMPORT_NO;
            }

            public String getINOCULATE() {
                return INOCULATE;
            }

            public void setINOCULATE(String INOCULATE) {
                this.INOCULATE = INOCULATE;
            }

            public String getKINETICS() {
                return KINETICS;
            }

            public void setKINETICS(String KINETICS) {
                this.KINETICS = KINETICS;
            }

            public String getPACK_COMPANY() {
                return PACK_COMPANY;
            }

            public void setPACK_COMPANY(String PACK_COMPANY) {
                this.PACK_COMPANY = PACK_COMPANY;
            }

            public String getPACK_NO() {
                return PACK_NO;
            }

            public void setPACK_NO(String PACK_NO) {
                this.PACK_NO = PACK_NO;
            }

            public String getRADIATE() {
                return RADIATE;
            }

            public void setRADIATE(String RADIATE) {
                this.RADIATE = RADIATE;
            }

            public String getRADIOACTIVE() {
                return RADIOACTIVE;
            }

            public void setRADIOACTIVE(String RADIOACTIVE) {
                this.RADIOACTIVE = RADIOACTIVE;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getTITLE() {
                return TITLE;
            }

            public void setTITLE(String TITLE) {
                this.TITLE = TITLE;
            }
        }
    }

}
