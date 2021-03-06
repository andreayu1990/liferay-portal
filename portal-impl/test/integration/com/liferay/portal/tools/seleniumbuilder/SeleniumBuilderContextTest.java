/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.tools.seleniumbuilder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Michael Hashimoto
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class SeleniumBuilderContextTest {

	public SeleniumBuilderContextTest() {
		try {
			_seleniumBuilderContext = new SeleniumBuilderContext(
				"./portal-web/test/functional/");
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Test
	public void testAction() throws Exception {
		test("Action.action");
	}

	@Test
	public void testActionCommandElement1009() throws Exception {
		test(
			"ActionCommandElement1009.action",
			"Error 1009: Duplicate command name click at " + _DIR_NAME +
				"/ActionCommandElement1009.action:8");
	}

	@Test
	public void testActionCommandElement2001() throws Exception {
		test(
			"ActionCommandElement2001.action",
			"Error 2001: Action command nameFail does not match a function " +
				"name at " + _DIR_NAME + "/ActionCommandElement2001.action:2");
	}

	@Test
	public void testActionFileName1008() throws Exception {
		test(
			"BaseLiferay.action",
			"Error 1008: Duplicate file name BaseLiferay at " + _DIR_NAME +
				"/BaseLiferay.action");
	}

	@Test
	public void testFunction() throws Exception {
		test("Function.function");
	}

	@Test
	public void testFunctionCommandElement1009() throws Exception {
		test(
			"FunctionCommandElement1009.function",
			"Error 1009: Duplicate command name name at " + _DIR_NAME +
				"/FunctionCommandElement1009.function:6");
	}

	@Test
	public void testFunctionFileName1008() throws Exception {
		test(
			"Click.function",
			"Error 1008: Duplicate file name Click at " + _DIR_NAME +
				"/Click.function");
	}

	@Test
	public void testMacro() throws Exception {
		test("Macro.macro");
	}

	@Test
	public void testMacroCommandElement1009() throws Exception {
		test(
			"MacroCommandElement1009.macro",
			"Error 1009: Duplicate command name name at " + _DIR_NAME +
				"/MacroCommandElement1009.macro:6");
	}

	@Test
	public void testMacroFileName1008() throws Exception {
		test(
			"BlogsEntry.macro",
			"Error 1008: Duplicate file name BlogsEntry at " + _DIR_NAME +
				"/BlogsEntry.macro");
	}

	@Test
	public void testTestCase() throws Exception {
		test("TestCase.testcase");
	}

	@Test
	public void testTestCaseCommandElement1009() throws Exception {
		test(
			"TestCaseCommandElement1009.testcase",
			"Error 1009: Duplicate command name name at " + _DIR_NAME +
				"/TestCaseCommandElement1009.testcase:6");
	}

	@Test
	public void testTestCaseFileName1008() throws Exception {
		test(
			"CPBlogsAcceptance.testcase",
			"Error 1008: Duplicate file name CPBlogsAcceptance at " +
				_DIR_NAME + "/CPBlogsAcceptance.testcase");
	}

	@Test
	public void testTestSuite() throws Exception {
		test("TestSuite.testsuite");
	}

	@Test
	public void testTestSuiteFileName1008() throws Exception {
		test(
			"CollaborationAcceptance.testsuite",
			"Error 1008: Duplicate file name CollaborationAcceptance at " +
				_DIR_NAME + "/CollaborationAcceptance.testsuite");
	}

	protected void test(String fileName) throws Exception {
		test(fileName, null, false);
	}

	protected void test(String fileName, String expectedErrorMessage)
		throws Exception {

		test(fileName, expectedErrorMessage, true);
	}

	protected void test(
			String fileName, String expectedErrorMessage,
			boolean expectException)
		throws Exception {

		String actualErrorMessage = null;

		try {
			_seleniumBuilderContext.addFile(_DIR_NAME + "/" + fileName);

			_seleniumBuilderContext.validateElements(
				_DIR_NAME + "/" + fileName);
		}
		catch (IllegalArgumentException iae) {
			actualErrorMessage = iae.getMessage();
		}
		finally {
			if (expectException) {
				Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
			}
			else {
				Assert.assertEquals(null, actualErrorMessage);
			}
		}
	}

	private static final String _DIR_NAME =
		"/../../../portal-impl/test/integration/com/liferay/portal/tools/" +
			"seleniumbuilder/dependencies";

	private static Log _log = LogFactoryUtil.getLog(
		SeleniumBuilderContextTest.class);

	private SeleniumBuilderContext _seleniumBuilderContext;

}