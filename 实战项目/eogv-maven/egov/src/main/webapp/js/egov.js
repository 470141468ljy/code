

    /**
     * ��������
     * @param lable
     * @param id
     * @constructor
     */
    FormItem=function (label,id) {
        this.label=label;
        this.id=id;
    }



    EGOV=function () {
        /**
         * ���зǿ���֤
         */
    this.checkForms=function (formItemArray) {
            for (var i=0;i<formItemArray.length;i++){
                var ids=formItemArray[i].id;
                var idsObj=document.getElementById(ids);
                if ((idsObj.value).trim()==""){
                    alert(formItemArray[i].label+"����Ϊ�գ�")
                    return false;
                }
            }
            return true;
        };

        /**
         * ��֤������ȷ�������Ƿ�һ��
         */
        this.SamePasd=function (formItem1,formItem2) {
            var id1=formItem1.id;
            var id2=formItem2.id;
            var id1Obj=document.getElementById(id1);
            var id2Obj=document.getElementById(id2);
            if (id1Obj.value!=id2Obj.value){
               id1Obj.value="";
               id1Obj.focus();
               id2Obj.value="";
                alert(formItem1.label+"��"+formItem2.label+"��һ����������д��")
                return false;
            }
            return true;
        }


    };
var eg=new EGOV();