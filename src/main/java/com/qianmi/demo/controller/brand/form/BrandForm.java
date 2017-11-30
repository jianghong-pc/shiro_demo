package com.qianmi.demo.controller.brand.form;

import com.qianmi.demo.controller.BaseForm;
import com.qianmi.demo.pojo.brand.Brand;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 * <p>Filename: com.qianmi.demo.controller.brand.form.BrandForm.java</p>
 * <p>Date: 2017-11-30 14:03.</p>
 *
 * @author <a href="mailto:jianghong@qianmi.com">of837-姜洪</a>
 * @since v0.0.1
 */
@Setter
@Getter
public class BrandForm extends BaseForm{

    private Integer id;

    private String name;

    public Brand toBrand() {
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(name);
        brand.setChainMasterId(super.getChainMasterId());
        return brand;
    }
}
